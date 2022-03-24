package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

/**
 * 描述
 * 现在有一种密码变换算法。
 * 九键手机键盘上的数字与字母的对应： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0，把密码中出现的小写字母都变成九键键盘对应的数字，如：a 变成 2，x 变成 9.
 * 而密码中出现的大写字母则变成小写之后往后移一位，如：X ，先变成小写，再往后移一位，变成了 y ，例外：Z 往后移是 a 。
 * 数字和其它的符号都不做变换。
 * 数据范围： 输入的字符串长度满足 1 < n < 100
 * 输入描述：
 * 输入一组密码，长度不超过100个字符。
 *
 * 输出描述：
 * 输出密码变换后的字符串
 *
 * 示例1
 * 输入：
 * YUANzhi1987
 * 输出：
 * zvbo9441987
 */
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
