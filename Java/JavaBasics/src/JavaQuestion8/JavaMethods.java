package JavaQuestion8;

import java.util.Scanner;

public class JavaMethods
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number one: ");
        int num1 = sc.nextInt();
        System.out.println("Enter number two: ");
        int num2 = sc.nextInt();
        System.out.println(add(num1, num2));
        System.out.println(subtract(num1, num2));
        System.out.println(multiply(num1, num2));
        System.out.println(divide(num1, num2));
    }

    public static int add(int a, int b)
    {
        return a + b;
    }
    public static int subtract(int a, int b)
    {
        return a - b;
    }

    public static double divide(int a, int b)
    {
        return a / b;
    }

    public static double multiply(int a, int b)
    {
        return a * b;
    }
}
