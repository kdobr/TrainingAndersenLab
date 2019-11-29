package linkedList.controller;

import linkedList.model.List;
import linkedList.model.MyLinkedList;

public class MyListController {

    public static void main(String[] args) {

        List<String> strList = new MyLinkedList<>();
        strList.add("Java");
        strList.add("JHTML");
        strList.add("CSS");
        strList.add("Javascript");
        strList.add("Angular");
        strList.add("Spring");
        strList.add("Hibernate");
        System.out.println(strList.getElementByIndex(0));
    }
}
