package com.maxlong.study.curator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:45
 * 类说明:
 */
public class SchedulerService {

    static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public static void startTimer(final String serverName){
        service.scheduleAtFixedRate(()->{
            System.out.println("我是服务器"+serverName +",开始已执行任务");
        }, 5,1, TimeUnit.SECONDS);

    }
    public static void stopTimer(final String serverName){
        System.out.println("我是服务器："+serverName+",停止执行任务");
        service.shutdown();

    }
}