package org.example;

/*
Assignment: Create a new Maven project to simulate employee management.
There are at least 2 categories of employees: Salaried and Contractual.
For salaried employess, salaries are calculated based on number of days present.
For contractual employees, it is based on number of hours worked.
Salaried employees are provided with some benefits (e.g., health).
Calculate the salaries for each type of employee.
 */

public class Employees
{
    private double Salary;

    public Employees() {}

    public Employees(double salary)
    {
        Salary = salary;
    }

    public double getSalary()
    {
        return Salary;
    }

    public void setSalary(double salary)
    {
        Salary = salary;
    }

    @Override
    public String toString()
    {
        return "Employees{" +
                "Salary=" + Salary +
                '}';
    }
}
