package com.maxlong.study.algorithm;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/5/15 10:31
 * 类说明:
 */
public class Greedy2 {

    public static void main(String[] args) {
        int[] houses = {2,4,5,6,7,9,11,12};
        int radii = 2;

        int[] allArea = new int[houses[houses.length - 1] + 1];
        for (int i = 0; i < houses.length; i++) {
            allArea[houses[i]] = houses[i];
        }

        int num = greedyNum(allArea, radii);
        System.out.println(num);
    }

    private static int greedyNum(int[] area, int radii) {
        int end = 0;
        for (int integer : area) {
            if(integer != 0){
                int te = integer + radii;
                if(te >= area.length){
                    te = area.length - 1;
                }
                int g = area[te];
                while (g == 0 && g >= integer) {
                    te = te - 1;
                }
                end = te + radii + 1;
                break;
            }
        }
        if(end >= area.length){
            return 1;
        }
        int[] child = Arrays.copyOfRange(area, end, area.length);
        return 1 + greedyNum(child, radii);
    }
}
