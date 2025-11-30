import java.util.Scanner;

public class FactorialPractice
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = sc.nextInt();
        System.out.println(factorial(n));
        System.out.println(factorialRecursive(n));
    }

    // Method calculates the factorial of a number normally using a method.
    public static int factorial(int n)
    {
        int result = 1;
        for (int i = 1; i <= n; i++)
        {
            result *= i;
        }
        return result;
    }
    // Method calculates the factorial of a number using recursion.
    public static int factorialRecursive(int n)
    {
        if (n > 1)
        {
            return n * factorialRecursive(n - 1);
        }
        else
        {
            return 1;
        }
    }
}