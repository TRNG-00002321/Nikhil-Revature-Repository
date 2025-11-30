package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UnCheckedDemo
{
    public static void main(String[] args)
    {
        int [] myArray= new int [5];
        try
        {
            FileInputStream fis = new FileInputStream("MyFile.txt");
            myArray[5] = 10;
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            System.out.println("Finally Block Executed");
        }
    }
}
