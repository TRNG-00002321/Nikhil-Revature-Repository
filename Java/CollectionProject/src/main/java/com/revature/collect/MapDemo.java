package com.revature.collect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo
{
    public static void main(String[] args)
    {
        Map<String, Double> persons = new HashMap<String,Double>();
        persons.put("Nikhil", 45.0);
        persons.put("Ian", 30.0);
        persons.put("Brandon", 45.0);
        System.out.println(persons.get("Nikhil"));

        Set names = persons.entrySet();

        Map<String, Integer> personAge = new HashMap<String,Integer>();
        personAge.put("Nikhil", 21);
        personAge.put("Ian", 22);
        personAge.put("Brandon", 23);
        System.out.println(personAge.get("Ian"));

        Set nameAge = personAge.entrySet();
        for(String key : personAge.keySet())
        {
            System.out.println("Key: " + key + ", Value: " + personAge.get(key));
        }

    }
}
