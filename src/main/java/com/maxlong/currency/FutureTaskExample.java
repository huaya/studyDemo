package com.maxlong.currency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/16 11:36
 * 类说明:
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do someting in callable!");
            Thread.sleep(1000);
            return "Done";
        });

        executorService.submit(futureTask);
        log.info("do something in main!");
        Thread.sleep(2000);
        String result = futureTask.get();
        log.info("result: {}", result);
        executorService.shutdown();
    }
}
