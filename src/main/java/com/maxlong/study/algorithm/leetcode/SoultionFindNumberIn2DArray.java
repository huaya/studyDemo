package com.maxlong.study.algorithm.leetcode;

public class SoultionFindNumberIn2DArray {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{};
        int target = 5;
        new SoultionFindNumberIn2DArray().findNumberIn2DArray(matrix, target);
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int i = 0, j = matrix[0].length - 1;
        while (true) {
            int num = matrix[i][j];

            boolean brk = false;
            if (num > target) {
                j--;
                brk = j < 0;
            } else if (num < target) {
                i++;
                brk = i == matrix.length;
            }
            if (brk || num == target) {
                return num == target;
            }
        }
    }

}
