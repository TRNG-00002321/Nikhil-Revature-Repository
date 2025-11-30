package org.example;

public class LambdaPractice
{
    public static void main(String[] args)
    {
        PrintHello hello = (firstName, lastName) -> "Hello " + firstName + " " + lastName;
        System.out.println(hello.printHello("John", "Doe"));
    }
}
