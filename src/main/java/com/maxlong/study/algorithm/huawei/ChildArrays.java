package com.maxlong.study.algorithm.huawei;

/**
 * Created on 2022/3/29.
 *
 * @author xxxx
 * @Email xxxx
 *
 * 遍历获得所有子集和
 */
public class ChildArrays {

    public static void main(String[] args) {
        int[] houses = {1, 2, -2, -3};
        hasChildEqual("", 0, houses);
        System.out.println(" " + houses[houses.length - 1]);
    }

    private static void hasChildEqual(String cl, int index, int[] houses) {
        String child = cl + " " + houses[index];
        System.out.println(child);
        for (int i = index + 1; i < houses.length; i++) {
            hasChildEqual(child, i, houses);
            if (i < houses.length - 1) {
                hasChildEqual("", i, houses);
            }
        }
    }
}
