package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;
import com.maxlong.study.lock.Semaphore;
import com.maxlong.study.zk.api.CuratorFrameworkManager;
import org.apache.curator.framework.recipes.locks.Lease;

import java.util.concurrent.TimeUnit;

/**
 * zk信号量
 *
 * Created by IntelliJ IDEA.
 * User: ma.xl
 * Date: 2015/11/29
 * Time: 15:24
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ZkSemaphore extends ZkPessimisticLock implements Semaphore<Lease> {

    public ZkSemaphore(CuratorFrameworkManager curatorFrameworkManager) {
        super(curatorFrameworkManager);
    }

    public Boolean lock() throws LockException {
        throw  new LockException("not supported");
    }

    public Boolean lock(long time, TimeUnit unit) throws LockException {
        throw  new LockException("not supported");
    }

    public void unlock() throws UnLockException {
        throw  new LockException("not supported");
    }
}
