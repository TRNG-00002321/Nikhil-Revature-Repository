package org.example;

public class CheckingAccount extends BankAccount
{

    public CheckingAccount(String accountId, String accountNumber, double balance) {
        super(accountId, accountNumber, balance);
    }

    @Override
    public double deposit(double amount)
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        double newBalance = getBalance() + amount;
        setBalance(newBalance);
        return newBalance;
    }

    @Override
    public double withdraw(double amount) throws WithDrawException
    {
        if (amount <= 0)
        {
            throw new WithDrawException("Withdrawal amount must be greater than zero.");
        }

        double fee = amount * 0.01;
        double total = amount + fee;

        double currentBalance = getBalance();

        if (total > currentBalance) {
            throw new WithDrawException(
                    "Insufficient funds. Need " + total + " but only " + currentBalance + " available."
            );
        }

        double newBalance = currentBalance - total;
        setBalance(newBalance);
        return newBalance;
    }

    @Override
    public String toString() {
        return "CheckingAccount{} " + super.toString();
    }
}
