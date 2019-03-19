package com.maxlong.algorithm;

import com.maxlong.study.utils.DateFormat;
import com.maxlong.study.utils.DateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-19 15:48
 */
public class FileErgodic {

    static List<String> fileNames = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("D:\\桌面文件\\工作");
        ergodic(file);
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

    public static List<String> ergodic(File file){
        if(file.isFile()){
            Long changeTime = file.lastModified();
            String changeAt = DateUtil.longToDate(changeTime, DateFormat.STYLE1);
            fileNames.add(changeAt + "   " + file.getName());
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                ergodic(f);
            }
        }
        return fileNames;
    }
}
