package com.example.firstspringboot.algorithm.linkDemo;

import lombok.Data;

import java.util.Stack;

/**
 * @Author :sunwenwu
 * @Date : 2020/12/9 10:26
 * @Description :二叉树：先、中、后序排列
 */
public class TwoBinaryTree_Stack {

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
        Stack<TwoNode> stack = new Stack<>();
        stack.push(head);

        //头、右、左
        TwoNode cur = null;
        while (!stack.isEmpty()){
            cur  = stack.pop();

            System.out.print(cur.value+",");
            if (cur.right != null) {

                stack.push(cur.right);
            }
            if (cur.left != null) {

                stack.push(cur.left);
            }
        }

        return;

    }

    public static void in(TwoNode head){

        if (head == null){
            return;
        }

        Stack<TwoNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null){
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                TwoNode node = stack.pop();
                System.out.print(node.value+",");
                head = node.right;
            }
        }

    }

    public static void after(TwoNode head){

        if (head == null){
            return;
        }
        Stack<TwoNode> stack = new Stack<>();
        Stack<TwoNode> stack1 = new Stack<>();
        stack.push(head);

        //头、右、左
        TwoNode cur = null;
        while (!stack.isEmpty()){
            cur  = stack.pop();

            stack1.push(cur);
            if (cur.left != null) {

                stack.push(cur.left);
            }
            if (cur.right != null) {

                stack.push(cur.right);
            }

        }

        while (!stack1.isEmpty()) {
            System.out.print(stack1.pop().value+",");
        }

        return;

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
