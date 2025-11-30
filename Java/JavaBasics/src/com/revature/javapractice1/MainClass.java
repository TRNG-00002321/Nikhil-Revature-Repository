package com.revature.javapractice1;

import com.revature.javapractice.Calculator;

public class MainClass
{
    public static void main(String[] args)
    {
        Calculator calc = new Calculator();
        int addResult = calc.add(1,2);
        System.out.println("Addition result is: " + addResult);
        System.out.println("Multiplication result is: " + calc.multiply(20, 40));
    }
}
