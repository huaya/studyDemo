package com.maxlong.study.algorithm;

/**
 * 在一个从左至右、从上到下都递增的矩阵[m*n]中，查找一个数是否存在
 */
public class ExistNum {

    public static void main(String[] args) {
        int[][] nums = {
                {1,  4,  7,  11, 15},
                {2,  5,  8,  12, 19},
                {3,  6,  9,  16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(exist(0, nums.length - 1, nums, 19, nums[0].length - 1));
        System.out.println(exist2(nums, 19));
    }

    /**
     * 1、先判断对角线两个数区间是不是包含目标数：num[0][0] < target < num[m - 1][n - 1] 否的话返回false.<br/>
     * 2、从右上角第一个数找起：num[0][n - 1], 大于target则向右找下一个，小于的话向下找下一个， 等于的话返回true.<br/>
     * 3、以此类推，直至找到边界，无法继续移动，设当前数为nums[i][j],两个终止情况【i == m - 1 && nums[i][j] < target】【j == n - 1 && nums[i][j] > target】<br/>
     */
    private static boolean exist2(int[][] nums, int target){
        int il = nums.length;
        int jl = nums[0].length;

        if(nums[0][0] > target){
            return false;
        }
        if(nums[il - 1][jl - 1] < target){
            return false;
        }

        int i = 0, s = jl - 1;
        while (true) {
            if(nums[i][s] == target){
                return true;
            } else if(nums[i][s] < target){
                i = i == il - 1 ? i : i + 1;
            } else if(nums[i][s] > target){
                s = s == 0 ? s : s - 1 ;
            }

            if(s == 0 && nums[i][s] > target){
                return false;
            }

            if(i == il - 1 && nums[i][s] < target){
                return false;
            }
        }
    }

    /**
     *  二分法，竖向将矩阵不断切分，
     *  判断子矩阵左右角区间是否包含目标数，不包含返回false
     *  无法切分时，遍历整行，查找是否存在
     */
    private static boolean exist(int i, int j, int[][] nums, int target, int l){
        if(i > j){
            return false;
        }
        if(i == j){
            for (int i1 : nums[j]) {
                if(i1 == target){
                    return true;
                }
            }
            return false;
        }

        if(nums[i][0] > target){
            return false;
        }
        if(nums[j][l] < target){
            return false;
        }
        int db = (j - i)/2 + i;
        return exist(i, db, nums, target, l) || exist(db + 1 , j, nums, target, l);
    }

}
