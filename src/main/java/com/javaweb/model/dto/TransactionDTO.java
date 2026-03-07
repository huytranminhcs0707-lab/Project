package com.javaweb.model.dto;

public class TransactionDTO extends AbstractDTO{

    private String code;
    private String transactionDetail;
    Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }
}
