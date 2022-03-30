package com.maxlong.study.algorithm.huawei;


import java.util.Arrays;

public class ArraySplit {

    public static void main(String[] args) {
        int[] nums = {1, 2, -2, 3};
        int target = 5;
        System.out.println(helper(0, nums, target));
    }

    private static boolean helper(int l, int[] list, int target){
        System.out.println(l + " " + target + " " + Arrays.toString(list));
        if (l == list.length) return target == 0;
        return helper(l + 1, list, target - list[l]) || helper(l + 1, list, target);
    }
}
