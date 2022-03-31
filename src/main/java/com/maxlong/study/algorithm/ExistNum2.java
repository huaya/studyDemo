package com.maxlong.study.algorithm;

public class ExistNum2 {

    public static void main(String[] args) {
        int[][] nums = {
                {1,  4,  7,  11, 15},
                {2,  5,  8,  12, 19},
                {3,  6,  9,  16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(exist(nums, 10));
    }

    private static boolean exist(int[][] nums, int tegart){
        int il = nums.length;
        int jl = nums[0].length;

        if(nums[0][0] > tegart){
            return false;
        }
        if(nums[il - 1][jl - 1] < tegart){
            return false;
        }

        int i = 0, s = jl - 1;
        while (true) {
            if(nums[i][s] == tegart){
                return true;
            } else if(nums[i][s] < tegart){
                i = i == il - 1 ? i : i + 1;
            } else if(nums[i][s] > tegart){
                s = s == 0 ? s : s - 1 ;
            }

            if(s == 0 && nums[i][s] > tegart){
                return false;
            }

            if(i == il - 1 && nums[i][s] < tegart){
                return false;
            }
        }
    }

}
