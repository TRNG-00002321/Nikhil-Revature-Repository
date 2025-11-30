package org.example;

public class Employee
{
    private String name;
    private int id;
    private int Salary;

    public Employee(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getSalary()
    {
        return Salary;
    }
    public void setSalary(int salary)
    {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", Salary=" + Salary +
                '}';
    }
}
