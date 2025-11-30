package org.example;

public class Contractual extends Employees implements EmployeeInterface
{
    private int hoursWorked;

    public Contractual() {}

    public Contractual(double salary)
    {
        super(salary);
    }

    @Override
    public double calculateSalary(int hoursWorked, double hourlyRate)
    {
        this.hoursWorked = hoursWorked;
        return hoursWorked * hourlyRate;
    }

    @Override
    public String toString()
    {
        return "Contractual{" +
                "hoursWorked=" + hoursWorked +
                "} " + super.toString();
    }

}
