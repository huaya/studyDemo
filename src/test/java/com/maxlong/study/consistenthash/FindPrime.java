package com.maxlong.study.consistenthash;

/**
 * Created on 2022/3/5.
 *
 * @author xxxx
 * @Email xxxx
 */
public class FindPrime {

    private static final int MAX_NUM = 10000000;

    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 2) {
            return true;
        }
        for (int i = 2; i * i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
