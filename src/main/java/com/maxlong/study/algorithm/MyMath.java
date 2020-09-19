package com.maxlong.study.algorithm;

/**
 * Created on 2020/9/16.
 *
 * @author xxxx
 * @Email xxxx
 */
public class MyMath {

    public static void main(String[] args) {
        System.out.println(sqrt(2));
    }

    public static double sqrt(int num) {
        float v = 1;
        float p = 0.000001f;
        while (Math.abs(num/v - v) >= p*v) {
            v = (num/v + v)/2;
        }
        return v;
    }
}
