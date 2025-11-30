package com.revature.myio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamsDemo
{;
    static FileInputStream fis;
    static FileOutputStream fos;
    public static void main(String[] args)
    {
        try
        {
            fis = new FileInputStream("example.txt");
            fos = new FileOutputStream("output1.txt");
            int x;
            //x = fis.read();
            while((x = fis.read()) != 1)
            {
                //System.out.println(x);
                fos.write(x);
            }
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
