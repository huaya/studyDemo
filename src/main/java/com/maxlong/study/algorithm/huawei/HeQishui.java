package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

/**
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足
 *
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 *
 * 输入描述:
 * 输入文件最多包含 10 组测试数据，每个数据占一行，仅包含一个正整数 n（ 1<=n<=100 ），表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 *
 *
 * 输出描述:
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 *
 *
 * 输入例子1:
 * 3
 * 10
 * 81
 * 0
 *
 * 输出例子1:
 * 1
 * 5
 * 40
 */
public class HeQishui {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int nums = in.nextInt();
            if(nums <= 0 ) break;
            int total = totalQshui(nums, 0);
            System.out.println(total);
        }
    }

    public static int totalQshui(int pingNum, int qishuiNum){
        if(pingNum <= 1){
            return qishuiNum;
        }
        int qiNum = pingNum/3;
        int wdNum = pingNum%3;
        int jNum = wdNum > 0 ? 3 - wdNum : 0;
        int cPnum = qiNum + (jNum > 0 ? 1 : 0);
        return totalQshui(cPnum - jNum, qishuiNum + cPnum);
    }

}
