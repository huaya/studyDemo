package com.maxlong.study.currency;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-04-18 0018 18:33
 * 类说明
 */
public class CollectionTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService poll = Executors.newFixedThreadPool(4);

//        List<String> coll = new ArrayList<>();
        Vector<String> coll = new Vector<>();

        Worker worker1 = new Worker(coll);
        Worker worker2 = new Worker(coll);
        Worker worker3 = new Worker(coll);
        Worker worker4 = new Worker(coll);
        poll.execute(worker1);
        poll.execute(worker2);
        poll.execute(worker3);
        poll.execute(worker4);

        poll.shutdown();
        while (!poll.isTerminated()){}
        System.out.println("All is finished!");
    }
}
