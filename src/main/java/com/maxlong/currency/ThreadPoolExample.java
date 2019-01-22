package com.maxlong.currency;

import com.maxlong.enums.ThreadPoolType;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/16 20:21
 * 类说明:
 */
public class ThreadPoolExample {

    private static ExecutorService executorService;

    private static ThreadPoolType poolType = ThreadPoolType.CACHE;

    public static void main(String[] args) throws Exception {

        try {
            if(poolType == ThreadPoolType.CACHE){
                executorService = Executors.newCachedThreadPool();
            } else if(poolType == ThreadPoolType.FIXED){
                executorService = Executors.newFixedThreadPool(3);
            } else if(poolType == ThreadPoolType.SCHEDULED){
                executorService = Executors.newScheduledThreadPool(3);
            } else if(poolType == ThreadPoolType.SINGLE){
                executorService = Executors.newSingleThreadExecutor();
            } else{
                throw new Exception("unsport threadpooltype");
            }

        } catch (Exception e){
            System.out.println(e);
        } finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }

    }
}
