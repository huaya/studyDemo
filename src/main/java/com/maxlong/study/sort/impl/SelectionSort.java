package com.maxlong.study.sort.impl;

import com.maxlong.study.sort.Sort;

/**选择排序：
 *
 * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
 *
 * 初始状态：无序区为R[1..n]，有序区为空；
 * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * n-1趟结束，数组有序化了。
 */
public class SelectionSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        for(int i = array.length; i > 0; i--){
            int mi = 0;
            for(int j = 1; j < i; j++){
                if(array[j] >= array[mi]){
                    mi = j;
                }
            }
            int tmp = array[mi];
            array[mi] = array[i - 1];
            array[i - 1] = tmp;
        }
        return array;
    }
}
