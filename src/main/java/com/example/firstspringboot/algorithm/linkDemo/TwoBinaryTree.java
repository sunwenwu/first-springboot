package com.example.firstspringboot.algorithm.linkDemo;

import lombok.Data;

/**
 * @Author :sunwenwu
 * @Date : 2020/12/9 10:26
 * @Description :二叉树：先、中、后序排列
 */
public class TwoBinaryTree {

    @Data
    public static class TwoNode {
        private Integer value;

        private TwoNode left;

        private TwoNode right;

        public TwoNode(Integer value){
            this.value = value;
        }
    }


    public static void main(String[] args) {
        TwoNode headNode = buildTree();

        pre(headNode);
        System.out.println("");
        in(headNode);
        System.out.println("");
        after(headNode);
    }


    public static void pre(TwoNode head){

        if (head == null){
            return;
        }
        System.out.print(head.value+",");
        pre(head.left);
        pre(head.right);

    }

    public static void in(TwoNode head){

        if (head == null){
            return;
        }
        in(head.left);
        System.out.print(head.value+",");
        in(head.right);

    }

    public static void after(TwoNode head){

        if (head == null){
            return;
        }
        after(head.left);
        after(head.right);
        System.out.print(head.value+",");

    }

    public static TwoNode buildTree(){

        TwoNode head = new TwoNode(1);
        TwoNode cur = head;
        int j = 1;
        cur.left = new TwoNode(++j);
        cur.right = new TwoNode(++j);

        cur.left.left = new TwoNode(++j);
        cur.left.right = new TwoNode(++j);
        cur.right.left = new TwoNode(++j);
        cur.right.right = new TwoNode(++j);

        return head;
    }
}
