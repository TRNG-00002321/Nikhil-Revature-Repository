package com.revature.myio;

import java.io.*;

public class FileReaderAndWrite
{;
    static FileReader fis;
    static FileWriter fos;
    public static void main(String[] args)
    {
        try
        {
            fis = new FileReader("example.txt");
            fos = new FileWriter("output1.txt");
            int x;
            //x = fis.read();
            while((x = fis.read()) != -1)
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
