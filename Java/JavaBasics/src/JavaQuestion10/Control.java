package JavaQuestion10;

public class Control
{
    public static int returnExample(int a)
    {
        return a;
    }
    public static void main(String[] args) {
        //Decision-making statements: if, if-else, if-else-if, and switch

        //If
        int a = 5;
        int b = 6;
        if (a < b) {
            System.out.println("B is greater than a");
        }

        //If-else
        if (a > b) {
            System.out.println("A is greater than b");
        } else {
            System.out.println("A is less than b");
        }

        //If-else-if
        if (a > b) {
            System.out.println("A is greater than b");
        } else if (b > a) {
            System.out.println("B is greater than a");
        }

        //Switch
        int x = 3;
        switch (x) {
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
        }

        //Looping statements: for, while, do-while

        //For loop
        b = 9;
        for (int i = 0; i < b; i++) {
            System.out.println(i);
        }

        //While loop
        while (b == 9) {
            System.out.println(b);
            b++;
        }

        //Jump/Branching statements: break, continue, return.

        //Break
        while (b == 9) {
            System.out.println(b);
            break;
        }

        //Continue
        for (int i = 0; i < 10; i++)
        {
            if (i == 9)
            {
                continue;
            }
            System.out.println(i);
        }

        //Return (Check function)
        returnExample(4);
    }
}