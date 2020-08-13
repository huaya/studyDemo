package com.maxlong.study.algorithm;

import java.io.*;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2020-08-11 22:55
 * 类说明:
 */
public class Exercises {

    public static void main(String[] args) throws IOException {
        hexToDec();
    }

    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
     */
    public static void hexToDec() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            str = str.replaceAll("^0[x|X]", "");
            int num = Integer.parseInt(str, 16);
            System.out.println(num);
        }
    }

    /**
     * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     */
    public static void splitString() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = new String[2];
        for (int i = 0; i < 2; i++) {
            strs[i] = b.readLine();
        }
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int l1 = 8 - str.length() % 8;
            for (int j = 0; l1 != 8 && j < l1; j++) {
                str = str + 0;
            }
            for (int k = 0; k < str.length(); k = k + 8) {
                System.out.println(str.substring(k, k + 8));
            }
        }
    }


    /**
     * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
     *
     * Input Param
     *
     * n               输入随机数的个数
     *
     * inputArray      n个随机整数组成的数组
     *
     *
     * Return Value
     *
     * OutputArray    输出处理后的随机整数
     *
     *
     * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
     *
     * 样例输入解释：
     * 样例有两组测试
     * 第一组是3个数字，分别是：2，2，1。
     * 第二组是11个数字，分别是：10，20，40，32，67，40，20，89，300，400，15。
     *
     */
    public static void randomNumber() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while ((str = b.readLine()) != null) {
            int n = Integer.parseInt(str);
            int[] num = new int[1001];
            for (int i = 1; i <= n; i++) {
                int h = Integer.parseInt(b.readLine());
                num[h] = h;
            }
            for (int i = 1; i < 1001; i++) {
                if (num[i] != 0)
                    System.out.println(num[i]);
            }
        }
    }

    /**
     * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
     */
    public static void wordNumber() {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        char word = input.next().charAt(0);
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char u1 = Character.toUpperCase(word);
            char u2 = Character.toUpperCase(str.charAt(i));
            if (u1 == u2) {
                num++;
            }
        }
        System.out.println(num);
    }

    /**
     * 计算字符串最后一个单词的长度，单词以空格隔开。
     */
    public static void wordlength() {
        Scanner input = new Scanner(System.in);
        String s = "";
        while (input.hasNextLine()) {
            s = input.nextLine();
            System.out.println(s.length() - 1 - s.lastIndexOf(" "));
        }
    }
}
