package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {
        /*
        Person p1 = new Person("Nikhil", "3312567644", new Address("12387 Main St", "Springfield", 62704));
        Person p2 = new Person("Alice", "9876543210", null);

        if(p1.getAddressOptional().isPresent())
        {
            System.out.println(p1.getAddressOptional().get().toString());
        }
        else {
            System.out.println("Address is null");
        }
         */

        Employee e1 = new Employee("John Doe", 101);
        Employee e2 = new Employee("Jane Smith", 102);

        // Employee [] employees = {e1, e2};

        /*
        for (Employee e : employees)
        {
            System.out.println(e.toString());
        }
        */

        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(e1.getId(), e1);
        employeeMap.put(e2.getId(), e2);
        System.out.println(employeeMap);
    }
}
