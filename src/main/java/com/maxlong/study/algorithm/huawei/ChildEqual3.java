package com.maxlong.study.algorithm.huawei;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息

public class ChildEqual3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            LinkedList<Integer> list = new LinkedList<>();
            int n = in.nextInt();
            int sum5 = 0;
            int sum3 = 0;
            int sum = 0;
            for (int i = 0; i < n; i++){
                int cur = in.nextInt();
                if (cur % 5 == 0){
                    sum5 += cur;
                }else if (cur % 3 == 0){
                    sum3 += cur;
                }else{
                    list.add(cur);
                }
                sum += cur;
            }
            int target = sum/2 - sum3;
            if (sum % 2 != 0) System.out.println("false");
            else System.out.println(helper(0, list, target));
        }
    }

    private static boolean helper(int l, LinkedList<Integer> list, int target){
        if (l == list.size()) return target == 0;
        return helper(l + 1, list, target - list.get(l)) || helper(l + 1, list, target);
    }
}