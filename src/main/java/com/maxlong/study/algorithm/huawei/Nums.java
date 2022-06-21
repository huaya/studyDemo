package com.maxlong.study.algorithm.huawei;

import java.util.ArrayList;
import java.util.List;

public class Nums {

    //在不使用额外的内存空间的条件下判断一个整数是否是回文数字
    public static void main(String[] args) {
        int num = -2123;
        System.out.println(isHuiwen2(num));
    }

    private static boolean isHuiwen2(int num) {
        int len = 1;
        while(num / len != 0) {
            len *= 10;
        }

        int inx = 1;
        while (len > inx) {
            if(num/inx%10 != num%len/(len/10)){
                return false;
            }
            inx *= 10;
            len /= 10;
        }
        return true;
    }

    private static boolean isHuiwen(int n) {
        int num = Math.abs(n);
        List<Integer> list = new ArrayList<>();
        do {
            list.add(num%10);
            num = num/10;
        } while ( num > 0);

        for(int i = 0; 2 * i < list.size(); i++){
            if(list.get(i) != list.get(list.size() - i - 1)){
                return false;
            }
        }
        return true;
    }
}
