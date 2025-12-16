package com.revature.Allure;

public class PaymentRequest
{

    private String cardNumber;
    private double amount;

    public PaymentRequest(String cardNumber, double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }
}
