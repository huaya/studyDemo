package com.maxlong.study.algorithm.leetcode;

import java.util.*;

public class SolutionShortestPathBinaryMatrix {
    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0,0,0},{0,1,1,1,1,1},{0,0,0,0,1,1},{0,1,0,0,0,1},{1,0,0,1,0,1},{0,0,1,0,1,0}};
        new SolutionShortestPathBinaryMatrix().shortestPathBinaryMatrix(grid);
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int i = grid.length;
        if (i == 0 || grid[0].length == 0) {
            return -1;
        }
        int j = grid[0].length;

        if (grid[0][0] != 0 || grid[i - 1][j - 1] != 0) {
            return -1;
        }
        grid[0][0] = 1;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int m = idx[0];
            int n = idx[1];
            int val = grid[m][n];

            if(grid[i - 1][j - 1] != 0 && val >= grid[i - 1][j - 1]){
                continue;
            }

            for (int x = m + 1; x >= 0 && x >= m - 1; x--) {
                if (x >= grid.length) {
                    continue;
                }

                for (int y = n + 1; y >= 0 && y >= n - 1; y--) {
                    if (y >= grid[0].length || (x == m && y == n)) {
                        continue;
                    }

                    int cur = grid[x][y];
                    if (cur == 0 || (grid[x][y] > val + 1)) {
                        grid[x][y] = val + 1;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }

        return grid[i - 1][j - 1] == 0 ? -1 : grid[i - 1][j - 1];
    }

}
