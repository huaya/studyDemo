package com.maxlong.study.sort;

import com.maxlong.study.sort.impl.BubbleSort;
import com.maxlong.study.sort.impl.InsertionSort;
import com.maxlong.study.sort.impl.SelectionSort;

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
            case SELECTION:
                return new SelectionSort();
            case INSERTION:
                return new InsertionSort();
            default:
                throw new IllegalArgumentException();
        }
    }

    public enum SortType{
        BUBBLE,
        SELECTION,
        INSERTION
    }
}
