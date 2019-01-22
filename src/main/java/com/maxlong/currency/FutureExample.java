package com.maxlong.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/16 11:28
 * 类说明:
 */
public class FutureExample {

    private static final Logger log = LoggerFactory.getLogger(FutureExample.class);

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {

            log.info("do someting in callable!");
            Thread.sleep(1000);
            return "Done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        log.info("do something in main!");
        Thread.sleep(2000);
        String result = future.get();
        log.info("result: {}", result);

        executorService.shutdown();

    }
}
