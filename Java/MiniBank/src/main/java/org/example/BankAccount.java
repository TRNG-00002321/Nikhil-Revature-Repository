package org.example;

public abstract class BankAccount
{
    private String accountId;
    private String accountNumber;
    private double balance;

    public BankAccount() {}

    public BankAccount(String accountId, String accountNumber, double balance)
    {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public abstract double deposit(double amount);
    public abstract double withdraw(double amount) throws WithDrawException;

    @Override
    public String toString()
    {
        return "BankAccount{" +
                "accountId='" + accountId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
