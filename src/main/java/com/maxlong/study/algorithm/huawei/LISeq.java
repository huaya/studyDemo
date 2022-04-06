package com.maxlong.study.algorithm.huawei;

import java.util.Arrays;

/**
 * 最长上升子序列（Longest  Increasing Subsequence），简称LIS，也有些情况求的是最长非降序子序列，二者区别就是序列中是否可以有相等的数。
 * 假设我们有一个序列 b i，当b1 < b2 < … < bS的时候，我们称这个序列是上升的。对于给定的一个序列(a1, a2, …, aN)，
 * 我们也可以从中得到一些上升的子序列(ai1, ai2, …, aiK)，这里1 <= i1 < i2 < … < iK <= N，
 * 但必须按照从前到后的顺序。比如，对于序列(1, 7, 3, 5, 9, 4, 8)，我们就会得到一些上升的子序列，如(1, 7, 9), (3, 4, 8), (1, 3, 5, 8)等等，
 * 而这些子序列中最长的（如子序列(1, 3, 5, 8) ），它的长度为4，因此该序列的最长上升子序列长度为4
 *
 * 把我们要求的问题简化成一个更小的子问题。子问题具有相同的求解方式，只不过是规模小了而已。最长上升子序列就符合这一特性。
 * 我们要求n个数的最长上升子序列，可以求前n-1个数的最长上升子序列，再跟第n个数进行判断。求前n-1个数的最长上升子序列，
 * 可以通过求前n-2个数的最长上升子序列……直到求前1个数的最长上升子序列，此时LIS当然为1。
 */
public class LISeq {

    public static void main(String[] args) {
        int[] seq = new int[]{186, 186, 150, 200, 160, 130, 197, 200};
//        dpLISeq(seq);
        binarySearchLISeq(seq);
    }

    /**
     * 二分查找
     */
    private static void binarySearchLISeq(int[] seq) {
        int[] nums = new int[seq.length + 1];
        int[] mcs = new int[seq.length];

        int index = 0;
        for(int i = 0; i < seq.length; i++){
            if(seq[i] > nums[index]){
                nums[++index] = seq[i];
                mcs = index;
            } else if (seq[i] < nums[index]){
                int s = 0, e = index;
                while (s < e) {
                    int mid = (s + e)/2;
                    if(nums[mid] > seq[i]){
                        e = mid;
                    } else if(nums[mid] < seq[i]){
                        s = mid + 1;
                    } else {
                        break;
                    }
                }
                l = s;
            }
            mcs[i] = l;
        }
        System.out.println(Arrays.toString(mcs));
    }


    /**
     * 动态规划实现
     */
    private static void dpLISeq(int[] seq) {
        int[] mcs = new int[seq.length];

        for(int i = 0; i < seq.length; i++){
            int max = 0;
            for(int j = 0; j < i; j++){
                if(seq[j] < seq[i]){
                    if(max < mcs[j]){
                        max = mcs[j];
                    }
                }
            }
            mcs[i] = max + 1;
        }
        System.out.println(Arrays.toString(mcs));
    }
}
