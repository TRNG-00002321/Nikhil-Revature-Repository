package com.revature.collect;

public class Person
{
    String name;
    int age;
    int id;

    public Person(String name, int age, int id)
    {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public Person()
    {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
