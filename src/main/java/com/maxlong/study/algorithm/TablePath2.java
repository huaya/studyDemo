package com.maxlong.study.algorithm;

/**
 * Created on 2020/4/11.
 *
 * @author MaXiaolong
 */
public class TablePath2 {

    public static void main(String[] args) {
        int num = getTraversal(5, 5);
        System.out.println(num);
    }

    public static int getTraversal(int p, int q) {
        int num = 0;
        if (p == 1 && q == 1) {
            return 1;
        }
        if (p > 1) {
            num += getTraversal(p - 1, q);
        }
        if (q > 1) {
            num += getTraversal(p, q - 1);
        }
        return num;
    }


}
