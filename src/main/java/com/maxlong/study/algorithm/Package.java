package com.maxlong.study.algorithm;

/**
 * Created on 2020/9/4.
 *
 * @author xxxx
 * @Email xxxx
 */
public class Package {

    public static void main(String[] args) {
        int money = 2000;
        int[] prices = {500, 400, 400, 300, 400, 400, 410, 200, 320, 500};
        int[] performance = {2000, 2000, 1200, 1500, 1600, 1600, 1230, 1000, 640, 500};

        int[][] f = new int[prices.length + 1][2001];


        for (int i = 1; i <= prices.length; i++) {
            for (int j = money; j > 0; j--) {
                if (prices[i] <= j) {
                    f[i][j] = max(f[i - 1][j], f[i - 1][j - prices[i]] + performance[i]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }

        System.out.println(f[9][1999]);
    }

    private static int max(int a, int b) {
        if(a > b){
            return a;
        } else {
            return b;
        }
    }
}