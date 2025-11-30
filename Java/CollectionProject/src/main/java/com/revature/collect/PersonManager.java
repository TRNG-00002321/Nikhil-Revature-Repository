package com.revature.collect;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PersonManager
{

    Person p1 = new Person("Nikhil", 21, 101);
    Person p2 = new Person("Alice", 22, 102);
    Person p3 = new Person("Bob", 23, 103);
    Person p4 = new Person("Jack", 21, 104);
    Person p5 = new Person("Eve", 22, 105);

    List<Person> people = new LinkedList<>();

    public PersonManager()
    {
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        people.add(p5);

        Iterator<Person> itr = people.iterator();
        while (itr.hasNext())
        {
            System.out.println(itr.next());
        }
    }

    public static void main(String[] args)
    {
        new PersonManager();
    }
}
