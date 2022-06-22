package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;
import com.maxlong.study.zk.api.CuratorFrameworkManager;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.TimeUnit;

/**
 * 不可重入锁
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/1 20:30
 * abacus-parent
 */
public class ZkDistributedSimpleLock extends ZkPessimisticLock {

    private final InterProcessSemaphoreMutex lock;

    public ZkDistributedSimpleLock(CuratorFrameworkManager curatorFrameworkManager, String lockPath) {
        super(curatorFrameworkManager);
        this.lock = new InterProcessSemaphoreMutex(curatorFrameworkManager.getCuratorFramework(), lockPath);
    }

    public Boolean lock() throws LockException {
        boolean acquire;
        try {
            lock.acquire();
            acquire = true;
        } catch (Exception e) {
            throw new LockException("lock failed,cause by:" + e.getMessage());
        }
        return acquire;
    }

    public Boolean lock(long time, TimeUnit unit) throws LockException {
        boolean acquire;
        try {
            acquire = lock.acquire(time, unit);
        } catch (Exception e) {
            throw new LockException("lock failed,cause by:" + e.getMessage());
        }
        return acquire;
    }

    public void unlock() throws UnLockException {
        try {
            lock.release();
        } catch (Exception e) {
            throw new UnLockException("unlock failed,cause by:" + e.getMessage());
        }
    }

}
