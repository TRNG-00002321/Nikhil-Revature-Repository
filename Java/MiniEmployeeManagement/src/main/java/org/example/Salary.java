package org.example;

public class Salary extends Employees implements EmployeeInterface
{
    private int daysPresent;

    public Salary() {}

    public Salary(double salary)
    {
        super(salary);
    }
    @Override
    public double calculateSalary(int daysPresent, double dailyWage)
    {
        this.daysPresent = daysPresent;
        return daysPresent * dailyWage;
    }

    @Override
    public String toString()
    {
        return "Salary{" +
                "daysPresent=" + daysPresent +
                "} " + super.toString();
    }
}
