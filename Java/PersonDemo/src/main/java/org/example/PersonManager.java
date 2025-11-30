package org.example;

public class PersonManager
{
    public static void main(String[] args)
    {
        Person p1 = new Person();
        Person p2 = new Person(34, "Alice");

        p1.setAge(34);
        p1.setName("Bob");

        System.out.println(p2.getAge());
        System.out.println(p2.getName());

        System.out.println(p1);
        System.out.println(p2);

        if (p1.equals(p2))
        {
            System.out.println("p1 is equal to p2");
        }
        else
        {
            System.out.println("p1 is not equal to p2");
        }
    }
}
