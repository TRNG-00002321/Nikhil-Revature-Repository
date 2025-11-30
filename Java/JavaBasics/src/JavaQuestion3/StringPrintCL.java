package JavaQuestion3;

public class StringPrintCL
{
    //Main method that prints all arguments passed through command line.
    public static void main(String[] args)
    {
        for (int i = 0; i <= args.length; i++)
        {
            String name = args[i];
            System.out.println(name);
        }
    }
}