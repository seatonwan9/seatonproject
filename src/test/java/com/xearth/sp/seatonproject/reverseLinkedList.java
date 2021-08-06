package com.xearth.sp.seatonproject;


import java.util.Collections;
import java.util.LinkedList;

public class reverseLinkedList {

    /**
     * 递归
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp = head.next;
//        System.out.println(temp);
        // 相当于压栈，先进后出的原则
        Node newHead = reverse(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 遍历
     * @param head
     * @return
     */
    public static Node reverse2(Node head) {
        Node prev = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            // 改变指向
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        head.next = b;
        b.next = c;
        c.next = d;

        System.out.println("反转前的链表指向：");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }

        System.out.println("\n反转后的链表指向：");
        head = reverse(head);
//        head = reverse2(head);
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }

        System.out.println();

//        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(3);
//        linkedList.add(4);
//
//        linkedList.forEach(obj -> {
//            System.out.print(obj + "->");
//        });
//
//        System.out.println();
//
//        Collections.reverse(linkedList);
//        linkedList.forEach(obj -> {
//            System.out.print(obj + "->");
//        });
    }
}
