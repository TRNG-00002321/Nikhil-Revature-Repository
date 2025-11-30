package org.example;

public class CalculatorLambda
{
    public static void main(String[] args) {
        printResult(2, 3, (a, b) -> a + b);
    }

    public static void printResult(int a, int b, Calculator calculator)
    {
        int result = calculator.add(a,b);
        System.out.println("The result is: " + result);
    }
}
