package com.maxlong.study.algorithm;

import com.maxlong.study.utils.DateFormat;
import com.maxlong.study.utils.DateUtil;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-19 15:48
 */
public class FileErgodic {

    static long begin = DateUtil.strToDate("2019-01-01 00:00:00", DateFormat.STYLE1).getTime();
    static long end = DateUtil.strToDate("2019-01-31 00:00:00", DateFormat.STYLE1).getTime();
    static ExecutorService service =  Executors.newCachedThreadPool();

    public static void main(String[] args) {
        File file = new File("F:\\");
        ergodic(file);
    }

    public static void ergodic(File file){
        if(file.isFile()){
            Long changeTime = file.lastModified();
            if(changeTime >= begin && changeTime <= end){
                String changeAt = DateUtil.longToDate(changeTime, DateFormat.STYLE1);
                System.out.println(changeAt + "   " + file.getPath());
            }
        } else {
            try {
                File[] files = file.listFiles();
                for (File f : files) {
                    service.execute(() -> ergodic(f));
                }
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("===============" + file.getPath() + "===========");
            }
        }
    }
}
