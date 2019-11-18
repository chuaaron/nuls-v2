package io.nuls.pocbft.constant;

/**
 * 命令名称
 * @author tag
 * 2019/10/16
 * */
public interface CommandConstant {
    /**
    * 向外提供的RPC接口名称
    * RPC interface name provided to the outside
    * */
    String CMD_CREATE_AGENT =  "cs_createAgent";
    String CMD_STOP_AGENT =  "cs_stopAgent";
    String CMD_GET_AGENT_LIST =  "cs_getAgentList";
    String CMD_GET_AGENT_INFO =  "cs_getAgentInfo";
    String CMD_GET_PACK_ADDRESS_LIST =  "cs_getNodePackingAddress";
    String CMD_GET_AGENT_ADDRESS_LIST =  "cs_getAgentAddressList";
    String CMD_GET_AGENT_STATUS =  "cs_getAgentStatus";
    String CMD_UPDATE_AGENT_CONSENSUS_STATUS =  "cs_updateAgentConsensusStatus";
    String CMD_UPDATE_AGENT_SATATUS =  "cs_updateAgentStatus";
    String CMD_GET_PACKER_INFO =  "cs_getPackerInfo";
    String CMD_GET_AGENT_CHANGE_INFO =  "cs_getAgentChangeInfo";

    String CMD_ADD_BLOCK =  "cs_addBlock";
    String CMD_VALID_BLOCK =  "cs_validBlock";
    String CMD_RECEIVE_HEADER_LIST =  "cs_receiveHeaderList";
    String CMD_CHAIN_ROLLBACK =  "cs_chainRollBack";

    String CMD_ADD_EVIDENCE_RECORD =  "cs_addEvidenceRecord";
    String CMD_ADD_DOUBLE_SPEND_RECORD =  "cs_doubleSpendRecord";
    String CMD_GET_PUNISH_LIST =  "cs_getPublishList";
    String CMD_GET_WHOLE_INFO =  "cs_getWholeInfo";
    String CMD_GET_INFO =  "cs_getInfo";
    String CMD_GET_ROUND_INFO =  "cs_getRoundInfo";
    String CMD_GET_ROUND_MEMBER_INFO =  "cs_getRoundMemberList";
    String CMD_GET_CONSENSUS_CONFIG =  "cs_getConsensusConfig";
    String CMD_GET_SEED_NODE_INFO =  "cs_getSeedNodeInfo";

    String CMD_DEPOSIT_TO_AGENT =  "cs_depositToAgent";
    String CMD_WITHDRAW =  "cs_withdraw";
    String CMD_GET_DEPOSIT_LIST =  "cs_getDepositList";

    String CMD_CREATE_MULTI_AGENT =  "cs_createMultiAgent";
    String CMD_STOP_MULTI_AGENT =  "cs_stopMultiAgent";
    String CMD_MULTI_DEPOSIT =  "cs_multiDeposit";
    String CMD_MULTI_WITHDRAW =  "cs_multiWithdraw";


    /**
     * 调用其他模块的RPC接口名称
     * RPC interface name to call other modules
     * */
    String CALL_AC_GET_UNENCRYPTED_ADDRESS_LIST = "ac_getUnencryptedAddressList";
    String CALL_AC_GET_PRIKEY_BY_ADDRESS= "ac_getPriKeyByAddress";
    String CALL_AC_GET_MULTI_SIGN_ACCOUNT = "ac_getMultiSignAccount";
    String CALL_AC_GET_SIGN_DIGEST = "ac_signDigest";
    String CALL_AC_SIGN_BLOCK_DIGEST = "ac_signBlockDigest";
    String CALL_AC_GET_ENCRYPTED_ADDRESS_LIST = "ac_getEncryptedAddressList";
    String CALL_AC_GET_ALIAS_BY_ADDRESS = "ac_getAliasByAddress";

    String CALL_BL_RECEIVE_PACKING_BLOCK= "receivePackingBlock";
    String CALL_BL_GET_LATEST_ROUND_BLOCK_HEADERS= "getLatestRoundBlockHeaders";
    String CALL_BL_GET_ROUND_BLOCK_HEADERS = "getRoundBlockHeaders";
    String CALL_BL_PUT_BZFT_FLAG= "putBZTFlag";

    String CALL_LG_GET_BALANCE_NONCE = "getBalanceNonce";
    String CALL_LG_GET_BALANCE = "getBalance";

    String CALL_TX_PACKABLE_TXS = "tx_packableTxs";
    String CALL_TX_CONFIRMED_TX = "tx_getConfirmedTx";
    String CALL_TX_NEW_TX = "tx_newTx";
    String CALL_TX_CONSENSUS_STATE = "tx_cs_state";
    String CALL_TX_BATCH_VERIFY = "tx_batchVerify";

    String CALL_PU_GET_VERSION = "getVersion";




    /**
     * 网络消息处理方法名称
     * Network message processing method name
     * */
    String MESSAGE_VOTE = "vote";
    String MESSAGE_GET_VOTE_RESULT = "getVoteResult";
    String MESSAGE_VOTE_RESULT = "voteResult";
}
