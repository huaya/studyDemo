package com.maxlong.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-04-18 0018 18:30
 * 类说明
 */
public class Worker extends Thread {

    private List<String> asyColl;

    private Vector<String>  sycColl;

    private Class aClass;

    public Worker() {
    }

    public Worker(Object coll) {
        aClass = coll.getClass();
        if(coll instanceof ArrayList<?>){
            this.asyColl = (List<String>) coll;
        } else if(coll instanceof Vector<?>){
            this.sycColl = (Vector<String>) coll;
        }
    }


    public void run(){
        String name = Thread.currentThread().getName();

        while (true){
            if(aClass ==  Vector.class){
                sycColl.add(name);
                System.out.println(name + ":" + sycColl.size());
            } else {
                asyColl.add(name);
                System.out.println(name + ":" + asyColl.size());
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
