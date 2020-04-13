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

        for(int i = 0; i < iLength; i++){
            for(int j = 0; j < jLength; j++){
                if(i == 0 && j == 0) continue;

                if(i == 0){
                    table[i][j] += table[i][j-1];
                }
                if(j == 0){
                    table[i][j] += table[i-1][j];
                }
                if(i > 0 && j > 0){
                    table[i][j] += Math.min(table[i-1][j], table[i][j-1]);
                }
            }
        }
        return table[iLength-1][jLength-1];
    }
}
