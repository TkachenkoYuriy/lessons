package org.jurassic.lessons;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {

        List strings1 = new ArrayList();
        strings1.add("a");
        strings1.add(1);
        if (strings1.get(0) instanceof String) {
            String str = (String) strings1.get(0);
            str.isEmpty();
        }


        List<String> strings2 = new ArrayList<>();
        strings2.add("a");
        List<Integer> integers = new ArrayList(strings2);
        integers.add(1);
        int second =integers.get(1);
        int first = integers.get(0);

        System.out.println(first);
        System.out.println(second);
    }

}
