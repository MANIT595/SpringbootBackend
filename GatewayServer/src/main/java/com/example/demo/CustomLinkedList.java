package com.example.demo;

public class CustomLinkedList {
    Node head;

    public void add(int i) {
        Node newNode = new Node(i);
        if(head == null){
            head = newNode;
        }
        else {
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printList() {
        Node current = head;
        while (current.next != null){
            System.out.println(current.val);
            current = current.next;
        }
        System.out.println(current.val);
    }

    public void delete(int i) {
        Node current = head;

        while (current.next != null){
            if(current.next.val == i){
                current.next = current.next.next;
                return;
            }
            current = current.next;

        }
    }

    public void addAfter(int i, int a) {
        Node newNode = new Node(i);
        if(head == null){
            head = newNode;
        }
        else {
            Node current = head;
            while (current.next != null){
                if(current.val == a){
                    newNode.next = current.next;
                    current.next = newNode;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
    }

    void sortLinkedList() {
        Node current = head;
        Node index = null;
        int temp;

        if (head == null) {
            return;
        } else {
            while (current != null) {
                // index points to the node next to current
                index = current.next;

                while (index != null) {
                    if (current.val > index.val) {
                        temp = current.val;
                        current.val = index.val;
                        index.val = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
    }

    public void printReverse() {
        Node previous = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            // Store next node
            next = current.next;
            // Reverse current node's pointer
            current.next = previous;
            // Move pointers one position ahead
            previous = current;
            current = next;
        }
        head = previous; // Update head to the new first element
    }
}
