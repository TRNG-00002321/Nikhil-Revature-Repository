package JavaQuestion5;

import java.util.Scanner;

public class Opertations
{
    //Main method that runs the program.
    public static void main(String[] args)
    {
        System.out.println("Enter a number: ");
        Scanner input = new Scanner(System.in);
        int number1 = input.nextInt();
        System.out.println("Enter another number: ");
        int number2 = input.nextInt();
        operations(number1,number2);
    }
    //Method that takes in two inputs and does basic calculations with them.
    public static void operations(int number1, int number2)
    {
        int add = number1 + number2;
        int subtract = number1 - number2;
        int divide =  number1 / number2;
        int multiply = number1 * number2;

        System.out.println("Addition is: " + add);
        System.out.println("Subtraction is: " + subtract);
        System.out.println("Divide is: " + divide);
        System.out.println("Multiply is: " + multiply);
    }
}
