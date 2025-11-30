package JavaQuestion9;

import static JavaQuestion9.JavaMethodsExtra.*;

public class Main
{
    public static void main(String[] args)
    {
        int a = 3;
        int b = 4;

        int sum = add(a, b);
        int subtract = subtract(a, b);
        double multiply = multiply(a, b);
        double divide = divide(a, b);

        System.out.println(sum);
        System.out.println(subtract);
        System.out.println(multiply);
        System.out.println(divide);

    }
}
