package JavaQuestion7;

public class Boolean
{
    //Main method that runs the program.
    public static void main(String[] args)
    {
        boolean a = true;
        boolean b = false;
        operations(a, b);
    }
    //Operations method that gets the output of the following problems.
    public static void operations(boolean a, boolean b)
    {
        boolean x = !a;
        boolean y = a|b;
        boolean z = ((!a & b) | (a & !b));
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }
}
