package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;
import com.maxlong.study.zk.api.CuratorFrameworkManager;

import java.util.concurrent.TimeUnit;

/**
 * 分布式悲观锁(读写分离)
 * <p/>
 * Created by IntelliJ IDEA.
 * User: ma.xl
 * Date: 2015/11/29
 * Time: 14:13
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ZkReadWritePessimisticLock extends ZkPessimisticLock {


    public ZkReadWritePessimisticLock(CuratorFrameworkManager curatorFrameworkManager) {
        super(curatorFrameworkManager);
    }

    /**
     * 加读锁
     *
     * @return
     * @throws LockException
     */
    public abstract Boolean readLock() throws LockException;

    /**
     * 加读锁
     *
     * @param time
     * @param unit
     * @return
     */
    public abstract boolean readLock(long time, TimeUnit unit) throws LockException;


    /**
     * 释放读锁
     *
     * @throws UnLockException
     */
    public abstract void readUnLock() throws UnLockException;

}
