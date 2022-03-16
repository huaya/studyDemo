package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;


/**
 * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * 数据范围：  ，输入的数字大小满足
 *
 * 输入描述:
 * 第一行先输入随机整数的个数 N 。
 * 接下来的 N 行每行输入一个整数，代表明明生成的随机数。
 * 具体格式可以参考下面的"示例"。
 *
 * 输出描述:
 * 输出多行，表示输入数据处理后的结果
 *
 *
 * 输入例子1:
 * 3
 * 2
 * 2
 * 1
 *
 * 输出例子1:
 * 1
 * 2
 */
public class Suijishupaixu {

    public static void main(String[] args) {
        int[] nums = new int[500];

        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        for(int i =0; i < length; i++){
            int num = in.nextInt();
            nums[num - 1] = num;
        }
        for(int i =0; i < nums.length; i++){
            int tmp = nums[i];
            if(tmp > 0){
                System.out.println(tmp);
            }
        }
    }

}
