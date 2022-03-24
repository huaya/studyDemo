package com.maxlong.study.algorithm.huawei;

public class Main4 {

    public static void main(String[] args) {
        char c2 = 'Y';
//        System.out.println('a' + 0);
//        System.out.println('A' + 0);
        System.out.println((char)(c2 + 32));
        System.out.println((char)(((c2 - 65)%26 + 1)%26 + 97));
    }
}
