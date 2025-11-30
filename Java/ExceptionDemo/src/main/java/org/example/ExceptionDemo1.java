package org.example;

import java.sql.SQLOutput;

public class ExceptionDemo1
{
    public static void main(String[] args)
    {
        int [] myArray = new int[5];

        try
        {
            myArray[5] = 10;
            System.out.println("Array initialized.");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }

        System.out.println("Ending Execution....");
    }
}
