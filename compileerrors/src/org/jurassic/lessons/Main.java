package org.jurassic.lessons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main{

    public static void main(String[] args) {

        GrandParent grandParent = new GrandParent();
        Parent parent = new Parent();
        Child child = new Child();

        List<GrandParent> grandParentList = new ArrayList<GrandParent>();
        grandParentList.add(grandParent);
        grandParentList.add(parent);
        grandParentList.add(child);

        grandParentList.get(0).grandParentMethod();
        grandParentList.get(1).parentMethod();
        grandParentList.get(2).childMethod();

        List<Parent> parentList = new ArrayList<>();
        parentList.add(grandParent);
        parentList.add(parent);
        parentList.add(child);

        parentList.get(0).grandParentMethod();
        parentList.get(1).parentMethod();
        parentList.get(2).childMethod();

        List<Child> childList = new ArrayList<>();
        childList.add(grandParent);
        childList.add(parent);
        childList.add(child);

        childList.get(0).grandParentMethod();
        childList.get(1).parentMethod();
        childList.get(2).childMethod();

        grandParentList = parentList;
        grandParentList = childList;

        parentList = grandParentList;
        parentList = childList;

        childList= grandParentList;
        childList = parentList;

        List<? extends GrandParent> extendedGrandParentList = new ArrayList<? extends GrandParent>();
        extendedGrandParentList.add(grandParent);
        extendedGrandParentList.add(parent);
        extendedGrandParentList.add(child);
        extendedGrandParentList.add(new Object());
        extendedGrandParentList.add(null);

        extendedGrandParentList.get(0).grandParentMethod();
        extendedGrandParentList.get(1).parentMethod();
        extendedGrandParentList.get(2).childMethod();

        extendedGrandParentList = grandParentList;
        extendedGrandParentList = parentList;
        extendedGrandParentList = childList;

        List<? super Parent> extendedParentList = new ArrayList<? super Parent>();
        extendedParentList.add(grandParent);
        extendedParentList.add(parent);
        extendedParentList.add(child);
        extendedParentList.add(new Object());
        extendedParentList.add(null);

        extendedParentList.get(0).grandParentMethod();
        extendedParentList.get(1).parentMethod();
        extendedParentList.get(2).childMethod();

        extendedParentList = grandParentList;
        extendedParentList = parentList;
        extendedParentList = childList;

        List<?> extendsObjectList = new ArrayList<>();

        extendsObjectList.add(grandParent);
        extendsObjectList.add(parent);
        extendsObjectList.add(child);
        extendsObjectList.add(new Object());
        extendsObjectList.add(null);

        extendsObjectList.get(0).grandParentMethod();
        extendsObjectList.get(1).parentMethod();
        extendsObjectList.get(2).childMethod();

        extendsObjectList = grandParentList;
        extendsObjectList = parentList;
        extendsObjectList = childList;

        List objectList = new ArrayList();

        objectList.add(grandParent);
        objectList.add(parent);
        objectList.add(child);

        objectList.get(0).grandParentMethod();
        objectList.get(1).parentMethod();
        objectList.get(2).childMethod();
    }

    public static void reverseWrong(List<?> list) {
        List<Object> tmp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size()-i-1));
        }
    }

    public static void reverseRight(List<?> list) {
        rev(list);
    }

    private static <T> void rev(List<T> list) {
        List<T> tmp = new ArrayList<T>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size()-i-1));
        }
    }

    public static <T extends Comparable<T>> T max(Collection<T> coll) {
        T candidate = coll.iterator().next();
        for (T elt : coll) {
            if (candidate.compareTo(elt) < 0) candidate = elt;
        }
        return candidate;
    }

    public static <T extends Object & Comparable<? super T>> T secMax(Collection<? extends T> coll) {
        T candidate = coll.iterator().next();
        for (T elt : coll) {
            if (candidate.compareTo(elt) < 0) candidate = elt;
        }
        return candidate;
    }

}
