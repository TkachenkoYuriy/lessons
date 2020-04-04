package org.jurassic.lessons;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        List<Integer> integers = new ArrayList(strings);
        integers.add(1);
        int first = integers.get(0);
        int second =integers.get(1);

        System.out.println(first);
        System.out.println(second);
    }

}
