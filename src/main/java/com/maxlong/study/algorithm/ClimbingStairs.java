package com.maxlong.study.algorithm;

/**
 * Created on 2020/4/11.
 * 假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，你能有多少种不同的方法爬到楼顶部？
 * 斐波拉契数
 * 使用递归，堆栈溢出
 * @author MaXiaolong
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int result = climbing(20);
        int result2 = climbing2(20);
        System.out.println(result + "-" + result2);
    }

    private static int climbing(int stairs) {
        int s1 = 0;
        int s2 = 1;
        int st = 0;
        int s = 1;
        while (s <= stairs) {
            st = s1 + s2;
            s1 = s2;
            s2 = st;
            s++;
        }
        return st;
    }

    private static int climbing2(int stairs) {
        if(stairs == 1) return 1;
        if(stairs == 2) return 2;
        return climbing2(stairs - 1) + climbing2(stairs - 2);
    }

}
