package com.revature.collect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListDemo
{
    public static void main(String[] args)
    {
        List<String> myList = new ArrayList<String>();
        LinkedList<String> myLinkedList = new LinkedList<String>();
        //myList.add(1);
        myList.add("two");
        myList.add("three");
        myList.add("Nikhil");

        myLinkedList.addAll(myList);
        myLinkedList.add(0, "zero");
        myLinkedList.clear();
        myLinkedList.contains("z");
        myLinkedList.remove("z");
        myLinkedList.removeAll(myLinkedList);

        Iterator itr2 = myLinkedList.iterator();
        while (itr2.hasNext())
        {
            System.out.println(itr2.next());
        }

        System.out.println(myList.get(1));

        Iterator itr = myList.iterator();
        while (itr.hasNext())
        {
            System.out.println(itr.next());
        }

        for(int i=0;i<myList.size();i++)
        {
            System.out.println(myList.get(i));
        }

        for(String i : myList)
        {
            System.out.println(i);
        }

        myList.remove(1);
        System.out.println(myList);
    }
}
