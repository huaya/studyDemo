package com.maxlong.study.algorithm.huawei;

import java.util.Arrays;

/**
 * Created on 2022/3/29.
 *
 * @author xxxx
 * @Email xxxx
 *
 * 是否存在两个子集和 和相等
 */
public class ChildEqual2 {

    public static void main(String[] args) {
        int[] houses = {1,2,-2,-3};
        int sum = Arrays.stream(houses).sum();
        if(sum % 2 == 0){
            int half = sum/2;
            boolean hh = hasChildEqual(0, 0, houses, half);
            if(hh){
                System.out.println(true);
            } else {
                System.out.println(houses[houses.length - 1] == half);
            }
        } else {
            System.out.println(false);
        }
    }

    private static boolean hasChildEqual(int cl, int index, int[] houses, int half) {
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
                if(th2){
                    return th2;
                }
            }
        }
        return false;
    }
}
