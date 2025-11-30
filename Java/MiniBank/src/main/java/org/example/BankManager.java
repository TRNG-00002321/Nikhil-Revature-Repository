package org.example;

import java.util.Scanner;

public class BankManager
{
    public static void main(String[] args) throws WithDrawException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Bank Manager Application!");
        System.out.print("Enter account ID: ");
        String accountID = input.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = input.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = input.nextDouble();

        System.out.println("Choose account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Choice: ");
        int typeChoice = input.nextInt();

        BankAccount account;

        if (typeChoice == 1)
        {
            account = new SavingsAccount(accountID, accountNumber, initialBalance);
        } else if (typeChoice == 2)
        {
            account = new CheckingAccount(accountID, accountNumber, initialBalance);
        } else
        {
            System.out.println("Invalid account type selected. Exiting.");
            return;
        }

        int menuChoice;
        do
        {
            printMenu();
            System.out.print("Enter choice: ");
            menuChoice = input.nextInt();

            switch (menuChoice)
            {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = input.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("New balance: " + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = input.nextDouble();
                    try {
                        account.withdraw(withdrawAmount);
                        System.out.println("New balance: " + account.getBalance());
                    } catch (WithDrawException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    if (account instanceof SavingsAccount)
                    {
                        System.out.println("Interest calculation not implemented yet.");
                    } else
                    {
                        System.out.println("Interest option only valid for Savings Accounts.");
                    }
                    break;
                case 4:
                    System.out.println(account);
                    break;

                case 5:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
        while (menuChoice != 5);
        input.close();
    }

    public static void printMenu()
    {
        System.out.println("Menu:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Calculate Interest (Savings only)");
        System.out.println("4. Display Account Details");
        System.out.println("5. Exit");
    }
}