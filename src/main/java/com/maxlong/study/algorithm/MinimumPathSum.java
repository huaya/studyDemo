package com.maxlong.study.algorithm;

/**
 * Created on 2020/4/11.
 *
 * @author MaXiaolong
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] table = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int pathNum = findMinPath(table);
        System.out.println(pathNum);
    }

    private static int findMinPath(int[][] table) {
        int iLength = table.length;
        int jLength = table[0].length;

        int i = 0;
        int j = 0;
        int pathNum = 0;
        while (i <= iLength && j <= jLength) {



        }
        return pathNum;
    }
}
