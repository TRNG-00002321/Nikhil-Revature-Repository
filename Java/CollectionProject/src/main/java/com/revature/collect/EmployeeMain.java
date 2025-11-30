package com.revature.collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeMain implements Comparator<Employee>
{
    public static void main(String[] args)
    {
        Employee emp = new Employee(1, "Nikhil", 10000);
        Employee emp2 = new Employee(2, "Josh", 20000);
        Employee emp3 = new Employee(3, "Peter", 30000);

        List<Employee> empList = new ArrayList<Employee>();

        empList.add(emp);
        empList.add(emp2);
        empList.add(emp3);

        Collections.sort(empList);

        //Display the list of employees
        for(Employee e : empList)
        {
            System.out.println(e);
        }

        //Display the list sorted by ID
        empList.sort((e1, e2) -> Integer.compare(e1.getId(),
                e2.getId()));
        System.out.println("Sorted by ID:");
        for(Employee e : empList) {
            System.out.println(e);
        }

        //Display the list sorted by Name
        empList.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("Sorted by Name:");
        for(Employee e : empList) {
            System.out.println(e);
        }

        //Display the list sorted by Salary
        empList.sort((e1, e2) -> Double.compare(e1.getSalary(),
                e2.getSalary()));
        System.out.println("Sorted by Salary:");
        for(Employee e : empList)
        {
            System.out.println(e);
        }
    }

    @Override
    public int compare(Employee o1, Employee o2)
    {
        return 0;
    }
}
