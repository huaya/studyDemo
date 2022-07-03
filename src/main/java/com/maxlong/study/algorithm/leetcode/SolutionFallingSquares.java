package com.maxlong.study.algorithm.leetcode;

import java.util.*;

public class SolutionFallingSquares {

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> result = new ArrayList<>();
        Set<int[]> ranges = new TreeSet<>((a, b) -> b[2] - a[2]);

        int maxHigh = 0;
        for (int i = 0; i < positions.length; i++) {
            int[] square = positions[i];
            if(i == 0){
                ranges.add(new int[]{square[0], square[0] + square[1], square[1]});
                maxHigh = square[1];
            } else {
                int x1 = square[0];
                int x2 = square[0] + square[1];
                int high = square[1];
                for (int[] range : ranges) {
                    if(x1 >= range[1] || x2 <= range[0]){
                        continue;
                    } else {
                        high = range[2] + high;
                        break;
                    }
                }
                maxHigh = Math.max(maxHigh, high);
                ranges.add(new int[]{x1, x2, high});
            }
            result.add(maxHigh);
        }
        return result;
    }

}
