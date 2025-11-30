package org.example;

import java.util.Scanner;

public class EmployeeManager
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter 1 for Salaried Employee or 2 for Contractual Employee: ");
            int choice = input.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Enter number of days present: ");
                    int daysPresent = input.nextInt();
                    System.out.println("Enter daily wage: ");
                    double dailyWage = input.nextDouble();
                    Salary salariedEmployee = new Salary();
                    double salariedSalary = salariedEmployee.calculateSalary(daysPresent, dailyWage);
                    System.out.println("Salaried Employee Salary: " + salariedSalary);
                    break;
                case 2:
                    System.out.println("Enter number of hours worked: ");
                    int hoursWorked = input.nextInt();
                    System.out.println("Enter hourly rate: ");
                    double hourlyRate = input.nextDouble();
                    Contractual contractualEmployee = new Contractual();
                    double contractualSalary = contractualEmployee.calculateSalary(hoursWorked, hourlyRate);
                    System.out.println("Contractual Employee Salary: " + contractualSalary);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
