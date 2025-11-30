package org.example;

import java.util.Optional;

public class Demo0
{
    public static void main(String[] args) {
        String [] words = new String[10];
        //String word = words[5].toLowerCase();
        //System.out.println(word);
        Optional<String> checkNull = Optional.ofNullable(words[5]);
        if(checkNull.isPresent()) {
            String word = words[5].toLowerCase();
            System.out.println(word);
        }
        System.out.println("Word is null");
    }
}
