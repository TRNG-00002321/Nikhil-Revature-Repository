package com.revature.collect;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo
{
    public static void main(String[] args)
    {
        //Use this if you want insertion order.
        Set<String> name = new LinkedHashSet<>();

        //Use this if you want sorted order.
        Set<String> names = new TreeSet<>();

        //Use this if you don't care about the order.
        Set<String> names1 = new HashSet<>();

        name.add("Nikhil");
        name.add("Ian");
        name.add("Brandon");

        names.add("Nikhil");
        names.add("Ian");
        names.add("Brandon");

        names1.add("Brandon");
        names1.add("Ian");
        names1.add("Nikhil");

        System.out.println(name);
        System.out.println(names);
        System.out.println(names1);

        for(String i : name)
        {
            System.out.println(i);
        }

        for(String y : names)
        {
            System.out.println(y);
        }

        for(String z : names1)
        {
            System.out.println(z);
        }
    }
}