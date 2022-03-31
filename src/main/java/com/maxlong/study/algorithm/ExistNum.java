package com.maxlong.study.algorithm;

public class ExistNum {

    public static void main(String[] args) {
        int[][] nums = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(exist(0, nums.length - 1, nums, 19, nums[0].length - 1));
    }

    private static boolean exist(int i, int j, int[][] nums, int tegart, int l){
        if(i > j){
            return false;
        }
        if(i == j){
            for (int i1 : nums[j]) {
                if(i1 == tegart){
                    return true;
                }
            }
            return false;
        }

        if(nums[i][0] > tegart){
            return false;
        }
        if(nums[j][l] < tegart){
            return false;
        }
        int db = (j - i)/2 + i;
        return exist(i, db, nums, tegart, l) || exist(db + 1 , j, nums, tegart, l);
    }

}
