package com.maxlong.consistenthash;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: ma.xl E-mail: ma.xl@gjmetal.com
 * @version 创建时间：2019-3-13 11:53
 * 类说明:
 */
public class HashCodeTest {

    @Test
    public void jdkHash() {
        String a = "a";
        String ab = "ab";
        String abc = "abc";
        System.out.println(a.hashCode());
        System.out.println('b' + 0);
        System.out.println(ab.hashCode());
        System.out.println(abc.hashCode());

    }
}
