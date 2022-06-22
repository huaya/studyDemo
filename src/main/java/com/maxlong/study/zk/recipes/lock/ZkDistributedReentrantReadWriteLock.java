package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;
import com.maxlong.study.zk.api.CuratorFrameworkManager;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * 读写锁
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/1 20:34
 * abacus-parent
 */
public class ZkDistributedReentrantReadWriteLock extends ZkReadWritePessimisticLock {

    private final InterProcessReadWriteLock lock;

    private final InterProcessMutex readLock;

    private final InterProcessMutex writeLock;

    public ZkDistributedReentrantReadWriteLock(CuratorFrameworkManager curatorFrameworkManager, String lockPath) {
        super(curatorFrameworkManager);
        lock = new InterProcessReadWriteLock(curatorFrameworkManager.getCuratorFramework(), lockPath);
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public Boolean lock() throws LockException {
        boolean acquire;
        try {
            writeLock.acquire();
            acquire = true;
        } catch (Exception e) {
            throw new LockException("lock failed,cause by:" + e.getMessage());
        }
        return acquire;
    }

    public Boolean lock(long time, TimeUnit unit) throws LockException {
        boolean acquire;
        try {
            acquire = writeLock.acquire(time, unit);
        } catch (Exception e) {
            throw new LockException("lock failed,cause by:" + e.getMessage());
        }
        return acquire;
    }

    public void unlock() throws UnLockException {
        try {
            writeLock.release();
        } catch (Exception e) {
            throw new UnLockException("unlock failed,cause by:" + e.getMessage());
        }
    }

    /**
     * 加读锁
     *
     * @return
     */
    public Boolean readLock() throws LockException {
        boolean acquire;
        try {
            readLock.acquire();
            acquire = true;
        } catch (Exception e) {
            throw new LockException("readLock failed,cause by:" + e.getMessage());
        }
        return acquire;
    }


    /**
     * 加读锁
     *
     * @param time
     * @param unit
     * @return
     */
    public boolean readLock(long time, TimeUnit unit) throws LockException {
        boolean acquire = false;
        try {
            acquire = readLock.acquire(time, unit);
        } catch (Exception e) {
            throw new LockException("readLock failed,cause by:" + e.getMessage());
        }
        return acquire;
    }


    /**
     * 释放读锁
     */
    public void readUnLock() throws UnLockException {
        try {
            readLock.release();
        } catch (Exception e) {
            throw new UnLockException("readUnLock failed,cause by:" + e.getMessage());
        }
    }

}
