package com.revature.parameterized;

public class Calculator {

    // Returns true if number is even
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    // Returns true if number is positive
    public boolean isPositive(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null");
        }
        return number > 0;
    }

    // Addition
    public int add(int a, int b) {
        return a + b;
    }

    // Subtraction
    public int subtract(int a, int b) {
        return a - b;
    }

    // Multiplication
    public int multiply(int a, int b) {
        return a * b;
    }

    // Division (integer division)
    public int divide(int a, int b) {
        return a / b;
    }

    public int power(int base, int exponent) {
        return (int) Math.pow(base, exponent);
    }

    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }
}
