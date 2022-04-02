package com.maxlong.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/5/15 10:31
 * 类说明:
 */
public class Greedy {

    public static void main(String[] args) {
        int[] houses = {2, 4, 5, 6, 7, 9, 11, 12};
        int radii = 2;

        //int num = greedyNum(0, houses, radii);
        int num = greedyNum2(houses, radii);
        System.out.println(num);
    }

    private static int greedyNum(int start, int[] houses, int radii) {
        if (start >= houses.length) {
            return 0;
        }
        int house = houses[start];
        int te = house + radii;

        int jzinx = start;
        for(int i = start + 1; i < houses.length; i++){
            int next = houses[i];
            if(next > te){
                break;
            } else {
                jzinx = i;
            }
        }

        int jzhs= houses[jzinx];

        int nextStart = jzinx + 1;
        for(int i = nextStart; i < houses.length; i++){
            if(houses[i] > jzhs + 2){
                nextStart = i;
                break;
            }
        }
        for(int i = start; i < nextStart; i++){
            System.out.print(houses[i] + " ");
        }
        System.out.println();
        return 1 + greedyNum(nextStart, houses, radii);
    }

    private static int greedyNum2(int[] houses, int radii) {
        List<List<Integer>> childs = new ArrayList<>();
        List<Integer> temp = Arrays.stream(houses).mapToObj(a -> Integer.valueOf(a)).collect(Collectors.toList());

        while (temp.size()>0){
            int begin = temp.get(0);
            int te = begin + radii;
            while(!temp.contains(te)){
                te = te - radii;
            }

            int x = te + radii;
            List<Integer> child = new ArrayList<>();
            for(int i =0; i<=x-begin; i++){
                if(temp.contains(begin + i)){
                    temp.remove(new Integer(begin + i));
                    child.add(begin + i);
                }
            }
            childs.add(child);
        }

        System.out.println(childs);
        return childs.size();
    }
}
