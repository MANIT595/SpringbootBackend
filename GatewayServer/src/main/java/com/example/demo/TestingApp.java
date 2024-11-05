package com.example.demo;


public class TestingApp {

    public static void main(String...args){
        CustomLinkedList list = new CustomLinkedList();
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(4);

        list.addAfter(6,3);

        list.addAfter(7,6);

        list.sortLinkedList();
        list.printList();

//        list.delete(3);
//
//        list.printList();
//
////        list.addAfter(4);
////
//        list.printReverse();
//        System.out.println("reverse");
//        list.printList();
//
//        System.out.println("add 4");
//        list.add(5);
//        list.printList();

    }
}
