package com.maxlong.study.practice;

import java.io.Closeable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/2 16:43
 * 类说明:
 */
public class CustomResource implements Closeable {

    @Override
    public void close(){
        System.out.println("close custom");
    }

    public static void main(String[] args) {
        try (CustomResource resource = new CustomResource()) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
