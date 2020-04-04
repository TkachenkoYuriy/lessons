package org.jurassic.lessons;

public class Parent<T extends Number> extends GrandParent implements Comparable<T> {

    T field;

    public void parentMethod() {
        System.out.println(field);
    }

    public T getField() {
        field.byteValue();
        return field;
    }

    public void print(T text) {

        System.out.println(text);
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}
