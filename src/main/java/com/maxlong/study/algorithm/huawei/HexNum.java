package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * 数据范围：保证结果在
 *
 * 输入描述:
 * 输入一个十六进制的数值字符串。
 *
 *
 * 输出描述:
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 *
 *
 * 输入例子1:
 * 0xAA
 *
 * 输出例子1:
 * 170
 */
public class HexNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String hexNum = in.nextLine();
            String num = hexNum.replaceAll("0x", "").replaceAll("0X", "");
            System.out.println(Integer.valueOf(num, 16));
        }
    }
}
