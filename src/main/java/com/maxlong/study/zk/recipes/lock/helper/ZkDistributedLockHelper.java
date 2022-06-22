package com.maxlong.study.zk.recipes.lock.helper;

import com.google.common.base.Preconditions;
import com.maxlong.study.exception.AcquireException;
import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;
import com.maxlong.study.utils.StringUtil;
import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import com.maxlong.study.zk.api.CuratorFrameworkManager;
import com.maxlong.study.zk.recipes.lock.*;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.utils.ZKPaths;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * ZK分布式锁管理辅助类
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/5/29 14:15
 * abacus-parent
 */
public class ZkDistributedLockHelper {

    private static CuratorFrameworkManager getCuratorFrameworkManager() {
        return CuratorFrameworkFactory.createCuratorFrameworkManager();
    }

    private static CuratorFrameworkManager getCuratorFrameworkManager(String connectionString, String namespace) {
        return CuratorFrameworkFactory.createCuratorFrameworkManager(connectionString, namespace);
    }

    public static ZkPessimisticLock getZkPessimisticLock(String module, String childPath, ZkDistributedLockType zkDistributedLockType) {
        ZkPessimisticLock zkPessimisticLock;
        CuratorFrameworkManager curatorFrameworkManager = getCuratorFrameworkManager();
        curatorFrameworkManager.start();
        String path = ZKPaths.makePath(module, childPath);
        if (ZkDistributedLockType.SIMPLE == zkDistributedLockType)
            zkPessimisticLock = new ZkDistributedSimpleLock(curatorFrameworkManager, path);
        else if (ZkDistributedLockType.REENTRANT == zkDistributedLockType)
            zkPessimisticLock = new ZkDistributedReentrantLock(curatorFrameworkManager, path);
        else if (ZkDistributedLockType.READWRITE == zkDistributedLockType)
            zkPessimisticLock = new ZkDistributedReentrantReadWriteLock(curatorFrameworkManager, path);
        else
            zkPessimisticLock = new ZkDistributedSimpleLock(curatorFrameworkManager, path);
        return zkPessimisticLock;
    }

    /**
     * 打开emaphore
     *
     * @param module
     * @param childPath
     * @param maxlease
     * @return
     */
    public static ZkSemaphore getZkSemaphore(String module, String childPath, int maxlease) {
        ZkSemaphore zkSemaphore;
        CuratorFrameworkManager curatorFrameworkManager = getCuratorFrameworkManager();
        curatorFrameworkManager.start();
        String path = ZKPaths.makePath(module, childPath);
        zkSemaphore = new ZkDistributedSemaphore(curatorFrameworkManager, path, maxlease);
        return zkSemaphore;
    }

    /**
     * 请求信号量
     *
     * @param zkSemaphore
     * @param qty
     * @param timeOut
     * @param timeUnit
     * @return
     */
    public static Collection<Lease> acquire(ZkSemaphore zkSemaphore, int qty, long timeOut, TimeUnit timeUnit) throws AcquireException {
        Collection<Lease> leases;
        try {
            leases = zkSemaphore.acquire(qty,timeOut,timeUnit);
        } catch (AcquireException e) {
            throw e;
        }
        return  leases;
    }

    /**
     * 释放信号量
     *
     * @param leases
     */
    public static void realse(ZkSemaphore zkSemaphore, Collection<Lease> leases){
        if(zkSemaphore != null)   zkSemaphore.realse(leases);
    }

    /**
     * 关闭semaphore
     *
     * @param zkSemaphore
     */
    public static void close(ZkSemaphore zkSemaphore){
        if(zkSemaphore != null) zkSemaphore.getCuratorFrameworkManager().close();
    }



    /**
     * 加悲观锁
     *
     * @param childPath
     * @return
     * @throws LockException
     */
    public static ZkPessimisticLock lock(String module, String childPath, ZkDistributedLockType zkDistributedLockType) throws LockException {
    	Preconditions.checkArgument(!StringUtil.isEmpty(childPath), "加悲观锁 childPath 不能为null");

        ZkPessimisticLock pessimisticLock;
        try {
            pessimisticLock = getZkPessimisticLock(module, childPath, zkDistributedLockType);
            if (pessimisticLock.lock())
                return pessimisticLock;
            else
                throw new LockException("lock failed");
        } catch (LockException e) {
            throw e;
        }
    }

    /**
     * 释放悲观锁
     *
     * @param pessimisticLock
     * @throws UnLockException
     */
    public static void unlock(ZkPessimisticLock pessimisticLock) throws UnLockException {
        try {
            if(pessimisticLock != null) pessimisticLock.unlock();
        } catch (UnLockException e) {
            throw e;
        } finally {
            if(pessimisticLock != null) pessimisticLock.getCuratorFrameworkManager().close();
        }
    }
}