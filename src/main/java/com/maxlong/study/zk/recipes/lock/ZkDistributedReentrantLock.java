package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;
import com.maxlong.study.zk.api.CuratorFrameworkManager;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.RevocationListener;
import org.apache.curator.framework.recipes.locks.Revoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/1 11:18
 * abacus-parent
 */
public class ZkDistributedReentrantLock extends ZkPessimisticLock {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZkDistributedReentrantLock.class);

    private final InterProcessMutex lock;

    public ZkDistributedReentrantLock(CuratorFrameworkManager curatorFrameworkManager, String lockPath) {
        super(curatorFrameworkManager);
        lock = new InterProcessMutex(curatorFrameworkManager.getCuratorFramework(), lockPath);
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

    /**
     * 可协商的撤销机制
     */
    public void makeRevocable(RevocationListener<InterProcessMutex> listener) {
        try {
            lock.makeRevocable(listener);
        } catch (Exception e) {
	        LOGGER.error("make revocable unknown exception:{}", e);
        }
    }

    /**
     * 撤销
     *
     * @param client
     * @param lockPath
     */
    public void revoke(CuratorFramework client, String lockPath) {
        try {
            Revoker.attemptRevoke(client, lockPath);
        } catch (Exception e) {
	        LOGGER.error("revoke unknown exception:{}", e);
        }
    }
}
