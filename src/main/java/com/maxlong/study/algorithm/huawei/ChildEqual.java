package com.maxlong.study.algorithm.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述
 * 输入int型数组，询问该数组能否分成两组，使得两组中各元素加起来的和相等，并且，所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），不是5的倍数也不是3的倍数能放在任意一组，可以将数组分为空数组，能满足以上条件，输出true；不满足时输出false。
 *
 * 数据范围：每个数组大小满足 1≤n≤50  ，输入的数据大小满足 ∣val∣≤500
 * 输入描述：
 * 第一行是数据个数，第二行是输入的数据
 *
 * 输出描述：
 * 返回true或者false
 *
 * 示例1
 * 输入：
 *  4
 *  1 5 -5 1
 *
 * 输出：
 * true
 *
 * 说明：
 *  第一组：5 -5 1
 *  第二组：1
 * 示例2
 * 输入：
 *  3
 *  3 5 8
 *
 * 输出：
 *  false
 *
 * 说明：由于3和5不能放在同一组，所以不存在一种分法。
 *
 * 思路：
 *  现计算数组分成三个子数组后的和，五的倍数和(fiveSum)、三的倍数和（threeSum)和其他和(otherSum)，并把所有的其他放入新的子数组（other）
 *  算出fiveSum和threeSum的差值， 将差值放入子数组other
 *  问题转化成子数组other是否存在两个和相等的子数组
 *
 *  如果otherSum是奇数，直接false
 *  如果otherSum是偶数，计算半数和(half), 有限穷举other的子数组和是否等于half，存在则返回true
 *
 */
public class ChildEqual {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int num = in.nextInt();

            int fiveSum = 0;
            int threeSum = 0;
            int otherSum = 0;
            int[] other = new int[num + 1];

            for(int i = 0; i < num; i++){
                int n = in.nextInt();
                if(n%5 == 0){
                    fiveSum += n;
                } else if(n%3 == 0){
                    threeSum += n;
                } else {
                    other[i] = n;
                    otherSum += n;
                }
            }
            int dif = fiveSum - threeSum;
            if(dif != 0){
                other[num] = dif;
                otherSum += dif;
            }

            int[] temp = Arrays.stream(other).filter(a -> a != 0).toArray();

            if(otherSum % 2 == 0){
                int half = otherSum/2;
                boolean hh = hasChildEqual(0, 0, temp, half);
                if(hh){
                    System.out.println(true);
                } else {
                    System.out.println(temp[temp.length - 1] == half);
                }
            } else {
                System.out.println(false);
            }
        }
    }

    private static boolean hasChildEqual(int cl, int index, int[] houses, int half) {
        if(houses.length == 0) {
            return true;
        }

        int sum = cl + houses[index];
        if(sum == half){
            return true;
        }

        for(int i = index + 1; i < houses.length; i++){
            boolean th = hasChildEqual(sum, i, houses, half);
            if(th){
                return true;
            }

            if(i < houses.length - 1){
                boolean th2 = hasChildEqual(0, i, houses, half);
                return th2;
            }
        }
        return false;
    }
}
