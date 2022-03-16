package com.maxlong.study.algorithm.huawei;

import java.util.HashMap;
import java.util.Map;
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
public class HexNum2 {

    public static void main(String[] args) {
        Map<String, Integer> hexMap = new HashMap<String, Integer>(){
            {
                put("0", 0);
                put("1", 1);
                put("2", 2);
                put("3", 3);
                put("4", 4);
                put("5", 5);
                put("6", 6);
                put("7", 7);
                put("8", 8);
                put("9", 9);
                put("A", 10);
                put("B", 11);
                put("C", 12);
                put("D", 13);
                put("E", 14);
                put("F", 15);
            }
        };
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String hexNum = in.nextLine();
            String num = hexNum.replaceAll("0x", "").replaceAll("0X", "");
            int length = num.length();
            int oxNum = 0;
            for(int i = 0; i < length; i++){
                String tmp = num.substring(length - 1 - i, length - i);
                int pow = hexMap.get(tmp);
                for(int j = 0; j < i; j ++) pow = pow * 16;
                oxNum = oxNum + pow;
            }
            System.out.println(oxNum);
        }
    }
}
