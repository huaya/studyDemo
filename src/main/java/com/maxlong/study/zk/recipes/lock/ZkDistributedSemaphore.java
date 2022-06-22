package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.exception.AcquireException;
import com.maxlong.study.zk.api.CuratorFrameworkManager;
import com.maxlong.study.zk.recipes.lock.helper.ZkDistributedLockHelper;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/1 20:44
 * abacus-parent
 */
public class ZkDistributedSemaphore extends ZkSemaphore {

    private final InterProcessSemaphoreV2 semaphore;

    public ZkDistributedSemaphore(CuratorFrameworkManager curatorFrameworkManager, String semaphorePath, int maxLease) {
        super(curatorFrameworkManager);
        semaphore = new InterProcessSemaphoreV2(curatorFrameworkManager.getCuratorFramework(), semaphorePath, maxLease);
    }

    /**
     * 获得租约
     *
     * @return
     */
    public Lease acquire() throws AcquireException {
        Lease lease ;
        try {
            lease = semaphore.acquire();
        } catch (Exception e) {
            throw new AcquireException("acquire failed,cause by:" + e.getMessage());
        }
        return lease;
    }

    /**
     * 获得租约
     *
     * @return
     */
    public Lease acquire(long time, TimeUnit unit)throws AcquireException{
        Lease lease;
        try {
            lease = semaphore.acquire(time, unit);
        } catch (Exception e) {
            throw new AcquireException("acquire failed,cause by:" + e.getMessage());
        }
        return lease;
    }

    /**
     * 获得租约
     *
     * @return
     */
    public Collection<Lease> acquire(int qty)throws AcquireException{
        Collection<Lease> leases;
        try {
            leases = semaphore.acquire(qty);
        } catch (Exception e) {
            throw new AcquireException("acquire failed,cause by:" + e.getMessage());
        }
        return leases;
    }


    /**
     * 获得租约
     *
     * @return
     */
    public Collection<Lease> acquire(int qty, long time, TimeUnit unit)throws AcquireException{
        Collection<Lease> leases;
        try {
            leases = semaphore.acquire(qty, time, unit);
        } catch (Exception e) {
            throw new AcquireException("acquire failed,cause by:" + e.getMessage());
        }
        return leases;
    }


    /**
     * 释放租约
     *
     * @param lease
     */
    public void realse(Lease lease) {
        semaphore.returnLease(lease);
    }

    /**
     * 释放租约
     *
     * @param leases
     */
    public void realse(Collection<Lease> leases) {
        semaphore.returnAll(leases);
    }

    public static void main(String[] args){
        ThreadGroup tg = new ThreadGroup("my-group");
        Thread t1 = new Thread(tg, () -> {
            ZkSemaphore zkSemaphore = ZkDistributedLockHelper.getZkSemaphore("aaaaa",ZkDistributedSemaphore.class.getName(),2);
            Lease lease1 = null;
            try{
                lease1 = zkSemaphore.acquire();
                System.out.println("thread-->"+Thread.currentThread()+",lease==>" + lease1);
                Thread.sleep(10000);
                System.out.println("thread-->"+Thread.currentThread()+", wake up");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                zkSemaphore.realse(lease1);
            }
        },"thread-1");
        t1.start();


        Thread t2 = new Thread(tg, () -> {
            ZkSemaphore zkSemaphore = ZkDistributedLockHelper.getZkSemaphore("aaaa",ZkDistributedSemaphore.class.getName(),1);
            Lease lease1 = null;
            try{
                lease1 = zkSemaphore.acquire();
                System.out.println("thread--->"+Thread.currentThread()+",lease==>" + lease1);
                Thread.sleep(10000);
                System.out.println("thread--->"+Thread.currentThread()+", wake up");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ZkSemaphore zkSemaphore1 = ZkDistributedLockHelper.getZkSemaphore("aaaa",ZkDistributedSemaphore.class.getName(),1);
                zkSemaphore1.realse(lease1);
            }
        },"thread-2");

        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
