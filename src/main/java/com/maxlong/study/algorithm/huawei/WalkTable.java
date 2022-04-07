package com.maxlong.study.algorithm.huawei;

/**
 * 描述
 * 请计算n*m的棋盘格子（n为横向的格子数，m为竖向的格子数）从棋盘左上角出发沿着边缘线从左上角走到右下角，总共有多少种走法，
 * 要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
 *
 * 注：沿棋盘格之间的边缘线行走
 *
 * 数据范围： 1≤n,m≤8
 *
 * 输入描述：
 * 输入两个正整数n和m，用空格隔开。(1≤n,m≤8)
 *
 * 输出描述：
 * 输出一行结果
 *
 * 示例1
 * 输入：
 *  2 2
 * 输出：
 *  6
 */
public class WalkTable {

    public static void main(String[] args) {
        int len = 2 + 1;
        int wid = 2 + 1;

        System.out.println(pathNum(len, wid));
        System.out.println(pathNum2(len, wid));
    }

    private static int pathNum2(int len, int wid){
        if(len == 1 || wid == 1){
            return 1;
        }
        return pathNum2(len - 1, wid) + pathNum2(len, wid - 1);
    }

    private static int pathNum(int len, int wid){
        int[][] dp = new int[len][wid];

        for(int i = 0; i < len; i++){
            for(int j = 0; j < wid; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                } else {
                    int up = i == 0 ? 0 : i - 1;
                    int down = j == 0 ? 0 : j - 1;
                    dp[i][j] = dp[up][j] + dp[i][down];
                }
            }
        }
        return dp[len - 1][wid - 1];
    }
}
