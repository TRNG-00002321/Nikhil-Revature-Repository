package org.example;

public class ThrowDemo
{
    public static void validateAge(int age) throws InvalidAgeException
    {
        if (age < 18)
        {
            throw new ArithmeticException("Age is not valid to vote.");
        }
        else
        {
            System.out.println("Welcome to vote!");
        }
    }

    public static void main(String[] args)
    {
        int age = 20;
        try {
            validateAge(age);
        } catch (InvalidAgeException e) {
            throw new RuntimeException(e);
        }
    }
}
