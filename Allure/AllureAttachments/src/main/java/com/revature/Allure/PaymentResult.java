package com.revature.Allure;

public class PaymentResult {

    private String status;
    private String transactionId;

    public PaymentResult(String status, String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
