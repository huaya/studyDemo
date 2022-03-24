package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

public class PasswdRevert {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String passwd = in.nextLine();
            char[] chars = passwd.toCharArray();
            for(int i = 0; i< chars.length; i++) {
                char c = chars[i];
                String cs = String.valueOf(c);
                if("abc".contains(cs)){
                    System.out.print('2');
                } else if ("def".contains(cs)) {
                    System.out.print('3');
                } else if ("ghi".contains(cs)) {
                    System.out.print('4');
                } else if ("jkl".contains(cs)) {
                    System.out.print('5');
                } else if ("mno".contains(cs)) {
                    System.out.print('6');
                } else if ("pqrs".contains(cs)) {
                    System.out.print('7');
                } else if ("tuv".contains(cs)) {
                    System.out.print('8');
                } else if ("wxyz".contains(cs)) {
                    System.out.print('9');
                } else if ('A' <= c && c <= 'Z') {
                    System.out.print((char)(((c - 65)%26 + 1)%26 + 97));
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }

}
