package io.nuls.api.model.po.db;

import com.google.common.base.Objects;

import java.math.BigInteger;

public class TxRelationInfo {

    private String txHash;

    private String address;

    private int type;

    private long createTime;

    private long height;

    private int chainId;

    private int assetId;

    private BigInteger values;

    private BigInteger fee;

    private BigInteger balance;

    // -1 : from , 1: to
    private int transferType;

    public TxRelationInfo() {

    }

    public TxRelationInfo(String address, TransactionInfo info, int chainId, int assetId, BigInteger values, int transferType, BigInteger balance) {
        this.address = address;
        this.txHash = info.getHash();
        this.type = info.getType();
        this.createTime = info.getCreateTime();
        this.height = info.getHeight();
        this.chainId = chainId;
        this.assetId = assetId;
        this.fee = info.getFee();
        this.values = values;
        this.balance = balance;
        this.transferType = transferType;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public BigInteger getValues() {
        return values;
    }

    public void setValues(BigInteger values) {
        this.values = values;
    }

    public BigInteger getFee() {
        return fee;
    }

    public void setFee(BigInteger fee) {
        this.fee = fee;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TxRelationInfo that = (TxRelationInfo) o;
        return type == that.type &&
                createTime == that.createTime &&
                height == that.height &&
                chainId == that.chainId &&
                assetId == that.assetId &&
                Objects.equal(txHash, that.txHash) &&
                Objects.equal(address, that.address) &&
                Objects.equal(values, that.values) &&
                Objects.equal(fee, that.fee) &&
                Objects.equal(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(txHash, address, type, createTime, height, chainId, assetId, values, fee, balance);
    }
}
