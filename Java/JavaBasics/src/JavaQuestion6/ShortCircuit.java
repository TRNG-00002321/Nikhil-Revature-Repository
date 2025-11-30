package JavaQuestion6;

import java.util.Scanner;

public class ShortCircuit
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number 1: ");
        int num1 = input.nextInt();
        System.out.print("Enter number 2: ");
        int num2 = input.nextInt();
        System.out.print("Enter number 3: ");
        int num3 = input.nextInt();
        System.out.println(getHighestNum(num1, num2, num3));
    }

    public static int getHighestNum(int a, int b, int c)
    {
        if (a > b && a > c)
        {
            return a;
        }
        else if (b > a && b > c)
        {
            return b;
        }
        else if (c > a && c > b)
        {
            return c;
        }
        else
        {
            return 0;
        }
    }
}
