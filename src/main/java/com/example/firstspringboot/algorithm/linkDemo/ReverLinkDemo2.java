package com.example.firstspringboot.algorithm.linkDemo;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :sunwenwu
 * @Date : 2020/11/25 18:38
 * @Description :
 */
public class ReverLinkDemo2 {

    @Data
    public static class Node {
        private Integer value;

        private Node next;

        public Node(int value){
            this.value = value;
        }

    }

    @Test
    public void run(){

        Node head1 = generateRandomLinkedList(10, 8);

        System.out.println(getLinkedListOriginOrder(head1));

        Node newHead = reverseLinkedList(head1);

        System.out.println(getLinkedListOriginOrder(newHead));
    }

    private Node reverseLinkedList(Node head) {

        Node pre = null;
        Node next = null;

        while (head != null){
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }


        return pre;
    }


    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));

        Node pre = head;

        while(size != 0){
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size --;
        }

        return head;
    }

    // for test
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }
}
