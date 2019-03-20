package com.maxlong.study.currency;

import lombok.extern.log4j.Log4j2;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/16 11:28
 * 类说明:
 */
@Log4j2
public class FutureExample {

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
