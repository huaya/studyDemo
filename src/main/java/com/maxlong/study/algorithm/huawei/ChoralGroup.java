package com.maxlong.study.algorithm.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2022/4/6.
 *
 * @author xxxx
 * @Email xxxx
 */
public class ChoralGroup {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();

            int[] group = new int[a];
            for (int i = 0; i < a; i++) {
                group[i] = in.nextInt();
            }
            choralGroupMinDel2(group);
        }
    }

    private static void choralGroupMinDel2(int[] arr) {
        int n = arr.length;

        int[] left = new int[n]; //存储每个数左边小于其的数的个数
        int[] right = new int[n];//存储每个数右边小于其的数的个数
        left[0] = arr[0];
        right[n - 1] = arr[n - 1];
        int num[] = new int[n];//记录以i为终点的从左向右和从右向走的子序列元素个数
        int index = 1;//记录当前子序列的长度
        for (int i = 1; i < n; i++) {
            if (arr[i] > left[index - 1]) {
                //直接放在尾部
                num[i] = index;//i左侧元素个数
                left[index++] = arr[i];//更新递增序列
            } else {
                //找到当前元素应该放在的位置
                int low = 0, high = index - 1;
                while (low < high) {
                    int mid = (low + high) / 2;
                    if (left[mid] < arr[i])
                        low = mid + 1;
                    else
                        high = mid;
                }
                //将所属位置替换为当前元素
                left[low] = arr[i];
                num[i] = low;//当前位置i的左侧元素个数
            }
        }
        index = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > right[index - 1]) {
                num[i] += index;
                right[index++] = arr[i];
            } else {
                int low = 0, high = index - 1;
                while (low < high) {
                    int mid = (high + low) / 2;
                    if (right[mid] < arr[i])
                        low = mid + 1;
                    else
                        high = mid;
                }
                right[low] = arr[i];
                num[i] += low;
            }
        }
        int max = 1;
        for (int number : num)
            max = Math.max(max, number);
        // max+1为最大的k
        System.out.println(n - max);

    }

    /**
     * 动态规划实现<br/>
     * 将问题转换成最长上升子序列问题（Longest  Increasing Subsequence）
     *
     * @See LISeq
     * <p>
     * 计算从左至右依次递增序列最长子序列
     * 计算从右至左依次递增序列最长子序列
     * 综合两个序列，获取能使队列最长的位置。队列长度减去最长子队列就是结果
     */
    private static void choralGroupMinDel(int[] group) {
        int[] left = new int[group.length];

        for (int i = 0; i < group.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (group[j] < group[i]) {
                    if (max < left[j]) {
                        max = left[j];
                    }
                }
            }
            left[i] = max + 1;
        }

        int[] right = new int[group.length];
        for (int i = group.length - 1; i > 0; i--) {
            int max = 0;
            for (int j = group.length - 1; j > i; j--) {
                if (group[j] < group[i]) {
                    if (max < right[j]) {
                        max = right[j];
                    }
                }
            }
            right[i] = max + 1;
        }

        int max = 0;
        for (int i = 0; i < group.length; i++) {
            int l = left[i];
            int r = right[i];
            max = Math.max(max, l + r - 1);
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        System.out.println(group.length - max);
    }

}
