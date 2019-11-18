package io.nuls.pocbft.utils.validator;

import io.nuls.base.basic.AddressTool;
import io.nuls.base.data.CoinData;
import io.nuls.base.data.Transaction;
import io.nuls.core.basic.Result;
import io.nuls.core.core.annotation.Autowired;
import io.nuls.core.core.annotation.Component;
import io.nuls.core.crypto.HexUtil;
import io.nuls.core.exception.NulsException;
import io.nuls.pocbft.constant.ConsensusErrorCode;
import io.nuls.pocbft.model.bo.Chain;
import io.nuls.pocbft.model.bo.tx.txdata.Agent;
import io.nuls.pocbft.utils.manager.AgentManager;
import io.nuls.pocbft.utils.manager.PunishManager;
import io.nuls.pocbft.utils.validator.base.BaseValidator;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.nuls.pocbft.utils.TxUtil.getSuccess;

/**
 * 创建节点交易验证器
 * @author  tag
 * */
@Component
public class CreateAgentValidator extends BaseValidator {
    @Autowired
    private PunishManager punishManager;
    @Autowired
    private AgentManager agentManager;
    @Override
    public Result validate(Chain chain, Transaction tx) throws NulsException,IOException{
        if (tx.getTxData() == null) {
            chain.getLogger().error("CreateAgent -- TxData is null");
            return Result.getFailed(ConsensusErrorCode.AGENT_NOT_EXIST);
        }
        Agent agent = new Agent();
        agent.parse(tx.getTxData(), 0);
        Result rs = createAgentBasicValid(chain, tx, agent);
        if (rs.isFailed()) {
            return rs;
        }
        rs = createAgentAddrValid(chain, tx, agent);
        if (rs.isFailed()) {
            return rs;
        }
        return getSuccess();
    }

    /**
     * 创建节点交易基础验证
     * Create agent transaction base validation
     *
     * @param chain 链ID/chain id
     * @param tx    创建节点交易/create transaction
     * @param agent 节点/agent
     * @return Result
     */
    private Result createAgentBasicValid(Chain chain, Transaction tx, Agent agent) throws NulsException, IOException {
        if (!AddressTool.validNormalAddress(agent.getPackingAddress(), (short) chain.getConfig().getChainId())) {
            chain.getLogger().error("CreateAgent -- PackingAddress error");
            return Result.getFailed(ConsensusErrorCode.ADDRESS_ERROR);
        }
        if (Arrays.equals(agent.getAgentAddress(), agent.getPackingAddress())) {
            chain.getLogger().error("CreateAgent -- AgentAddress is the same as  packingAddress");
            return Result.getFailed(ConsensusErrorCode.AGENTADDR_AND_PACKING_SAME);
        }
        if (Arrays.equals(agent.getRewardAddress(), agent.getPackingAddress())) {
            chain.getLogger().error("CreateAgent -- RewardAddress is the same as  packingAddress");
            return Result.getFailed(ConsensusErrorCode.REWARDADDR_AND_PACKING_SAME);
        }
        if (tx.getTime() <= 0) {
            chain.getLogger().error("CreateAgent -- Transaction creation time error");
            return Result.getFailed(ConsensusErrorCode.DATA_ERROR);
        }
        BigInteger deposit = agent.getDeposit();
        if (deposit.compareTo(chain.getConfig().getDepositMin()) < 0 || deposit.compareTo(chain.getConfig().getDepositMax()) > 0) {
            chain.getLogger().error("CreateAgent -- The mortgage exceeds the boundary value");
            return Result.getFailed(ConsensusErrorCode.DEPOSIT_OUT_OF_RANGE);
        }
        CoinData coinData = new CoinData();
        coinData.parse(tx.getCoinData(), 0);
        Result rs = appendDepositCoinDataValid(chain, agent.getDeposit(), coinData, agent.getAgentAddress());
        if (rs.isFailed()) {
            return rs;
        }
        //验证手续费是否足够
        rs = validFee(chain, coinData, tx);
        if (rs.isFailed()) {
            return rs;
        }
        return getSuccess();
    }

    /**
     * 创建节点交易节点地址及出块地址验证
     * address validate
     *
     * @param chain 链ID/chain id
     * @param tx    创建节点交易/create transaction
     * @param agent 节点/agent
     * @return boolean
     */
    private Result createAgentAddrValid(Chain chain, Transaction tx, Agent agent) {
        if (!chain.getSeedNodeList().isEmpty()) {
            byte[] nodeAddressBytes;
            //节点地址及出块地址不能是种子节点
            for (String nodeAddress : chain.getSeedNodeList()) {
                nodeAddressBytes = AddressTool.getAddress(nodeAddress);
                if (Arrays.equals(nodeAddressBytes, agent.getAgentAddress())) {
                    chain.getLogger().error("CreateAgent -- AgentAddress is seedNode address");
                    return Result.getFailed(ConsensusErrorCode.AGENT_EXIST);
                }
                if (Arrays.equals(nodeAddressBytes, agent.getPackingAddress())) {
                    chain.getLogger().error("CreateAgent -- PackingAddress is seedNode address");
                    return Result.getFailed(ConsensusErrorCode.AGENT_PACKING_EXIST);
                }
            }
        }
        //节点地址及出块地址不能重复
        List<Agent> agentList = agentManager.getAgentList(chain, chain.getNewestHeader().getHeight());
        if (agentList != null && agentList.size() > 0) {
            Set<String> set = new HashSet<>();
            for (Agent agentTemp : agentList) {
                if (agentTemp.getTxHash().equals(tx.getHash())) {
                    chain.getLogger().error("CreateAgent -- Transaction already exists");
                    return Result.getFailed(ConsensusErrorCode.TRANSACTION_REPEATED);
                }
                set.add(HexUtil.encode(agentTemp.getAgentAddress()));
                set.add(HexUtil.encode(agentTemp.getPackingAddress()));
            }
            boolean b = set.contains(HexUtil.encode(agent.getAgentAddress()));
            if (b) {
                chain.getLogger().error("CreateAgent -- AgentAddress address is already in use");
                return Result.getFailed(ConsensusErrorCode.AGENT_EXIST);
            }
            b = set.contains(HexUtil.encode(agent.getPackingAddress()));
            if (b) {
                chain.getLogger().error("CreateAgent -- Agent packaging address is already in use");
                return Result.getFailed(ConsensusErrorCode.AGENT_PACKING_EXIST);
            }
        }
        long count = punishManager.getRedPunishCount(chain, agent.getAgentAddress());
        if (count > 0) {
            chain.getLogger().error("CreateAgent -- Red card address cannot create node again");
            return Result.getFailed(ConsensusErrorCode.LACK_OF_CREDIT);
        }
        return getSuccess();
    }
}
