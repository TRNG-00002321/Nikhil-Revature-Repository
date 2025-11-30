package org.example;

import java.util.Objects;

public class Person
{
    private int age;
    private String name;

    public Person(int age, String name)
    {
        this.age = age;
        this.name = name;
    }

    public Person()
    {
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Person person = (Person) obj;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(age);
    }
    
    @Override
    public String toString()
    {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}