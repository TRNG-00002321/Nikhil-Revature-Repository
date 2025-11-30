package com.revature.collect;

public class Employee implements Comparable<Employee>
{
    private int id;
    private String name;
    private double salary;

    //Create constructor, getter, setter, toString methods. In another class create
    // 5 employees. And add them to a list. Display the list. Display the list sorted by ID. Display the list
    // sorted by Name. Display the list sorted by Salary.

    public Employee() {}

    public Employee(int id, String name, double salary)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
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

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee o)
    {
        return 0;
    }
}
