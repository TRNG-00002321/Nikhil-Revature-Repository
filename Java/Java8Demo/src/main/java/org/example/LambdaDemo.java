package org.example;

public class LambdaDemo
{
    public static void main(String[] args)
    {
        Calculator calculator = (n1, n2) -> n1 + n2;
        System.out.println(calculator.add(3,4));
    }
}
