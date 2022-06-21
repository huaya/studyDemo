package com.maxlong.study.sort.impl;

import com.maxlong.study.sort.Sort;

import java.util.Arrays;

/**
 * 插入排序：<br/>
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 *
 * 从第一个元素开始，该元素可以认为已经被排序；
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 将新元素插入到该位置后；
 * 重复步骤2~5。
 */
public class InsertionSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        for(int i = 1; i < array.length; i++){
            int tmp = array[i];
            int idx = i - 1;
            for(;idx >= 0 && array[idx] > tmp; idx--){
                array[idx + 1] = array[idx];
            }
            array[idx + 1] = tmp;
        }
        return array;
    }
}
