package com.example.firstspringboot.algorithm.linkDemo;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :sunwenwu
 * @Date : 2020/11/23 10:19
 * @Description :
 */
public class reverLinkDemo {

    @Data
    public static class Node {
        private Integer value;

        private Node next;

        public Node(int value){
            this.value = value;
        }
    }

    @Test
    public void test1(){
        Node head1 = generateRandomLinkedList(10, 8);

        System.out.println(getLinkedListOriginOrder(head1));

        Node newHead = reverseLinkedList(head1);

        System.out.println(getLinkedListOriginOrder(newHead));



    }

    //  head
    //   a    ->   b    ->  c  ->  null
    //   c    ->   b    ->  a  ->  null
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            //1、先把当前头结点的next结点保存
            next = head.next;
            //2、保存完以后可以指向上一个结点
            head.next = pre;
            //3、讲上一个结点的引用指向当前头结点，做为下个头结点的上一个结点
            pre = head;
            //4、更新当前头结点，使用第1步保存的结点
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
