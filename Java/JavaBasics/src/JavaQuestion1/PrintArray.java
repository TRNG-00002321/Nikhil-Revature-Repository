package JavaQuestion1;
import java.util.Scanner;

/*
Name: Nikhil Reddy Nalabolu
Date: 11/12/2024
Print a 2D Array: Write a program to initialize and print
the elements of a given 2D integer array (matrix).
 */

public class PrintArray
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of columns: ");
        int n = sc.nextInt();
        System.out.println("Enter number of rows: ");
        int m = sc.nextInt();
        int[][] userArray = new int[n][m];
        System.out.println("Enter elements: ");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                userArray[i][j] = sc.nextInt();
            }
        }
        System.out.println("Here's your 2D array printed: ");
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<m;j++)
            {
                System.out.print(userArray[i][j]+" ");
            }
        }
    }
}