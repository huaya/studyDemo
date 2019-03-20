package com.maxlong.study.algorithm;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-20 17:26
 */
public class FileFind {

    static String pattern1  = "reflect";
    static String pattern2  = "Reflect";
    static ExecutorService service =  Executors.newCachedThreadPool();

    public static void main(String[] args) {
        File file = new File("D:\\code\\abacus");
        ergodic(file);
    }

    public static void ergodic(File file){
        if(file.isFile()){
            String fileName = file.getName();
            if(fileName.contains(pattern1) || fileName.contains(pattern2)){
                System.out.println(file.getPath());
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
