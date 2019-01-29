package com.maxlong.algorithm;


import com.maxlong.study.utils.FileUtil;

import java.io.File;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-1-13 17:17
 */
public class Temp {

    public static void main(String[] args) {
//        String res = FileUtil.readFileToStr("resources/json/temp.json");
        File file = new File(System.getProperty("user.dir"));
        System.out.println(file.getName());
//        System.out.printf(res);

    }
}
