package org.jurassic;

import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args) {

        GrandParent grandParent = new GrandParent();
        Parent parent = new Parent();
        Child child = new Child();

        List<GrandParent> grandParentList = new ArrayList<>();
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

        

    }
}
