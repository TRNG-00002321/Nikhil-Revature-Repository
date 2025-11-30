package JavaQuestion2;

import java.util.Scanner;

public class Sum2DArray
{
    public static void main(String[] args)
    {
        Scanner x = new Scanner(System.in);
        System.out.println("Enter number of columns: ");
        int n = x.nextInt();
        System.out.println("Enter number of rows: ");
        int m = x.nextInt();
        int[][] userArray = new int[n][m];
        System.out.println("Enter elements: ");
        int sum = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++) {
                userArray[i][j] = x.nextInt();
                sum += userArray[i][j];
            }
        }
        System.out.println("Sum of 2D is: " + sum);
    }
}
