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

    public static final int[] array;

    static {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(random.nextInt(100000));
        }
        array = list.stream().mapToInt(Integer::valueOf).toArray();
    }

}
