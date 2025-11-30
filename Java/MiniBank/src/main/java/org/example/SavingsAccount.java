package org.example;

public class SavingsAccount extends BankAccount implements SimpleInterest
{
    public SavingsAccount(String accountId, String accountNumber, double balance)
    {
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
    public double withdraw(double amount) throws WithDrawException {
        if (amount <= 0) {
            throw new WithDrawException("Withdrawal amount must be greater than zero.");
        }

        double currentBalance = getBalance();

        if (amount > currentBalance) {
            throw new WithDrawException("Insufficient funds. Cannot withdraw " + amount);
        }

        double newBalance = currentBalance - amount;
        setBalance(newBalance);
        return newBalance;
    }

    @Override
    public String toString()
    {
        return "SavingsAccount{} " + super.toString();
    }

    @Override
    public double calculateInterest(double percentage)
    {
        if (percentage < 0)
        {
            throw new IllegalArgumentException("Interest percentage must be non-negative.");
        }
        return getBalance() * (percentage / 100);
    }

}
