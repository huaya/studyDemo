package com.maxlong.study.sort;

import com.maxlong.study.sort.impl.BubbleSort;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019-04-30 11:06
 * 类说明:
 */
public class SortFactory {

    public static final Sort build(SortType sortType) {
        switch (sortType){
            case BUBBLE:
                return new BubbleSort();
            default:
                throw new IllegalArgumentException();
        }

    }
    public enum SortType{
        BUBBLE
    }
}
