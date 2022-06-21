package com.maxlong.study.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class SolutionSquare {

    public static void main(String[] args) {
        int[] p1 = {0, 0};
        int[] p2 = {1, 1};
        int[] p3 = {1, 0};
        int[] p4 = {0, 1};

        System.out.println(new SolutionSquare().validSquare(p1, p2, p3, p4));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        int d12 = distancePoint(p1, p2);
        int d13 = distancePoint(p1, p3);
        int d14 = distancePoint(p1, p4);
        int d23 = distancePoint(p2, p3);
        int d24 = distancePoint(p2, p4);
        int d34 = distancePoint(p3, p4);

        Set<Integer> distances = new HashSet<>();
        distances.add(d12);
        distances.add(d13);
        distances.add(d14);
        distances.add(d23);
        distances.add(d24);
        distances.add(d34);

        return distances.size() == 2 && !distances.contains(0);
    }

    private int distancePoint(int[] p1, int[] p2) {
        int i = p1[0] - p2[0];
        int j = p1[1] - p2[1];
        return i * i + j * j;
    }
}
