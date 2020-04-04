package org.jurassic.lessons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main{

    public static void main(String[] args) {

        GrandParent grandParent = new GrandParent();
        Parent parent = new Parent();
        Child child = new Child();
        Parent<String> parent2 = new Parent<>();

        List<GrandParent> grandParentList = new ArrayList<GrandParent>();
        grandParentList.add(grandParent);
        grandParentList.add(parent);
        grandParentList.add(child);

        grandParentList.get(0).grandParentMethod();
        grandParentList.get(1).parentMethod();
        grandParentList.get(2).childMethod();

        if(grandParentList.get(1) instanceof Parent) {
            ((Parent) grandParentList.get(1)).parentMethod();
        }

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

        /*
            PECS
            producer - extends
            consumer - super
         */

        List<? extends GrandParent> extendedGrandParentList = new ArrayList<GrandParent>();
        extendedGrandParentList = new ArrayList<Parent>();
        extendedGrandParentList = new ArrayList<Child>();
        extendedGrandParentList = new ArrayList<Object>();
        //extendedGrandParentList =  ...
        extendedGrandParentList.add(grandParent);
        extendedGrandParentList.add(parent);
        extendedGrandParentList.add(child);
        extendedGrandParentList.add(new Object());
        extendedGrandParentList.add(null);

        extendedGrandParentList.get(0).grandParentMethod();
        extendedGrandParentList.get(1).parentMethod();
        extendedGrandParentList.get(2).childMethod();

        List<Object> objectParamList = new ArrayList<>();

        extendedGrandParentList = objectParamList;
        extendedGrandParentList = grandParentList;
        extendedGrandParentList = parentList;
        extendedGrandParentList = childList;

        List<? super Parent> extendedParentList = new ArrayList<>();
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
        //List<? extends Object> extendsObjectList

        extendsObjectList.add(grandParent);
        extendsObjectList.add(parent);
        extendsObjectList.add(child);
        extendsObjectList.add(new Object());
        extendsObjectList.add(null);

        extendsObjectList.get(0).toString();
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

        objectList = extendedGrandParentList;

        extendedGrandParentList = objectList;
        extendedParentList = objectList;
        childList = objectList;


        Collection<Parent> collection = new ArrayList<>();
        collection.add(parent);
        collection.add(parent2);

        Collection<GrandParent> collection2 = new ArrayList<>();
        collection2.add(new GrandParent());
        collection2.add(new GrandParent());

        max(collection);

        max(collection2);
    }

//    public static void reverse(List<?> list) {
//
//    }

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

    public static <T extends Number & Comparable<? super T>> T secMax(Collection<? extends T> coll) {
        T candidate = coll.iterator().next();
        for (T elt : coll) {
            if (candidate.compareTo(elt) < 0) candidate = elt;
        }
        return candidate;
    }

}
