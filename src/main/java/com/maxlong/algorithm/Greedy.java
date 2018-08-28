package com.maxlong.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/5/15 10:31
 * 类说明:
 */
public class Greedy {
    public static void main(String[] args) {
//        int[] house = {1,2,3,4,5};int radii = 1;
        int[] house = {2,4,5,6,7,9,11,12};int radii = 2;

        List<List<Integer>> childs = new ArrayList<>();
        List<Integer> temp = creareList(house);

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

        System.out.println(childs.size());
        System.out.println(childs);
    }

    private static List<Integer> creareList(int[] house) {
        List<Integer> list = new ArrayList<>();
        for(int i : house){
            list.add(i);
        }
        return list;
    }
}
