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

//    final static String pattern  = "(.*)(?i)zookeeper(.*)\\.java$|(.*)(?i)zk(.*)\\.java$";
    final static String pattern  = "(.*)(?i)DateUtil(.*)\\.java$";
    final static String path = "D:\\workspace-gj";
    final static ExecutorService service =  Executors.newCachedThreadPool();

    public static void main(String[] args) {
        File file = new File(path);
        ergodic(file);
    }

    public static void ergodic(File file){
        if(file.isFile()){
            String fileName = file.getName();
            if(fileName.matches(pattern)){
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
