package com.maxlong.study.algorithm;

import java.math.BigDecimal;

/**
 * @describe：You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * @author： ma.xl
 * @datetime： 2018-10-9 12:06
 */
public class AddTwoNumbers {


    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode p = l1,q = l2,temp = l3;
        int carry = 0;
        while (p != null || q != null){
            int i = p == null?0:p.val;
            int j = q == null?0:q.val;
            temp.next = new ListNode((i + j + carry)%10);
            carry = (i + j  + carry)/10;
            p = p == null?null:p.next;
            q = q == null?null:q.next;
            temp = temp.next;
        }
        if(carry > 0){
            temp.next  = new ListNode(carry);
        }
        return l3.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal num1 = toNumber(l1);
        BigDecimal num2 = toNumber(l2);
        BigDecimal num3 = num1.add(num2);
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        String num3Str = num3.toString();

        ListNode l3 = new ListNode(Integer.parseInt(num3Str.substring(0,1)));
        for(int i = 1; i<num3Str.length();i++){
            ListNode x = new ListNode(Integer.parseInt(num3Str.substring(i,i+1)));
            x.next = l3;
            l3 = x;
        }
        return l3;
    }

    public static BigDecimal toNumber(ListNode listNode){
        ListNode temp = listNode;
        BigDecimal num = BigDecimal.ZERO;
        BigDecimal tenPow = BigDecimal.ONE;
        for(int i = 0; temp != null; i++){
            num =  num.add(new BigDecimal(temp.val).multiply(tenPow));
            temp = temp.next;
            tenPow = tenPow.multiply(new BigDecimal(10));
        }
        return num;
    }

    public static void main(String[] args) {
        ListNode l1 =  makeListNode(new int[]{6,1,7});
        ListNode l2 = makeListNode2(new int[]{5,6,4});
//        ListNode l1 =  makeListNode(new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1});
//        ListNode l2 = makeListNode2(new int[]{5,6,4});
        ListNode l3 = addTwoNumbers2(l1,l2);
        System.out.println(toNumber(l3));
    }

    public static ListNode makeListNode(int[] array){
        ListNode listNode = null;
        for(int i = array.length - 1; i >= 0; i--){
          ListNode temp =  new ListNode(array[i]);
          temp.setNext(listNode);
          listNode = temp;
        }
        return listNode;
    }

    public static ListNode makeListNode2(int[] array){
        ListNode listNode = new ListNode(0);
        ListNode temp = listNode;

        for(int i = 0; i < array.length; i++){
            temp.next = new ListNode(array[i]);
            temp = temp.next;
        }
        return listNode.next;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode setNext(ListNode next){
            this.next = next;
            return this;
        }
    }
}