package com.maxlong.study.algorithm;

/**
 * Created on 2020/6/18.
 *
 * @author MaXiaolong
 * @Email hu5624548@163.com
 */
public class SingleNumber {
    /**
     * @param a: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] a) {
        if (a.length == 1) return a[0];
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }

        for (int k = 0; k < a.length; k = k + 2) {
            if (k == a.length - 1) return a[k];
            if (a[k] != a[k + 1]) {
                if (a[k + 1] == a[k + 2]) {
                    return a[k];
                } else {
                    return a[k + 1];
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 5, 3, 3, 4, 4};

        int tmp = a[0];
        for (int i = 1; i < a.length; i++) {
            tmp = tmp ^ a[i];
        }
        System.out.println(tmp);
    }
}