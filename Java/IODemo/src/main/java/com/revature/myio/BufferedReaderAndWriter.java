package com.revature.myio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderAndWriter
{

    public void lineStreamMethod(String inputFile, String outputFile) throws IOException
    {

        try (
                BufferedReader inputStream = new BufferedReader(new FileReader(inputFile));
                BufferedWriter outputStream = new BufferedWriter(new FileWriter(outputFile))
        ) {

            String line;
            while ((line = inputStream.readLine()) != null) {
                outputStream.write(line);
                outputStream.newLine();  // keeps the formatting
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
