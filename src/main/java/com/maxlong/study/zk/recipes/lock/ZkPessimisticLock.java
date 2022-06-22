package com.maxlong.study.zk.recipes.lock;

import com.maxlong.study.lock.PessimisticLock;
import com.maxlong.study.zk.api.CuratorFrameworkManager;

/**
 * Zk分布式悲观锁
 *
 * Created by IntelliJ IDEA.
 * User: ma.xl
 * Date: 2015/11/29
 * Time: 14:36
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ZkPessimisticLock extends PessimisticLock {

    protected final CuratorFrameworkManager curatorFrameworkManager;

    public ZkPessimisticLock(CuratorFrameworkManager curatorFrameworkManager){
        this.curatorFrameworkManager = curatorFrameworkManager;
    }

    public CuratorFrameworkManager getCuratorFrameworkManager() {
        return curatorFrameworkManager;
    }
}
