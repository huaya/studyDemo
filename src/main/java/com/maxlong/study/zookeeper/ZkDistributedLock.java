package com.maxlong.study.zookeeper;

import com.maxlong.study.zk.recipes.lock.ZkDistributedLockType;
import com.maxlong.study.zk.recipes.lock.ZkPessimisticLock;
import com.maxlong.study.zk.recipes.lock.helper.ZkDistributedLockHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-29 15:11
 */
public class ZkDistributedLock {

    private static final int THREAD_NUM = 10;

    private static AtomicInteger threadId = new AtomicInteger(1);

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool(r -> new Thread(r, "Thread-" + threadId.getAndIncrement()));

        for (int i = 0; i < THREAD_NUM; i++) {

            service.execute(() -> {
                ZkPessimisticLock lock = ZkDistributedLockHelper.lock("maxlong","test", ZkDistributedLockType.REENTRANT);
                while (lock != null){
                    System.out.println(Thread.currentThread().getName() + "获取锁成功，赶紧干活！");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ZkDistributedLockHelper.unlock(lock);
                    break;
                }
            });
        }

        service.shutdown();
    }
}
