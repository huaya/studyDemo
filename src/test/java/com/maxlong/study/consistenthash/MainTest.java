package com.maxlong.study.consistenthash;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-22 17:55
 */
public class MainTest {

    public static void main(String[] args) throws ClassNotFoundException {
        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getClassName());
        }
    }
}
