package com.maxlong.study.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019-04-30 11:22
 * 类说明:
 */
public class ArrayData {

    public static int[] createBigArray(){
        return createBigArray(100000);
    }

    public static int[] createBigArray(int len){
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(random.nextInt(len));
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }


    public static int[] createSingleNumArray(int length){
        int[] a = new int[length];
        return a;
    }
}
