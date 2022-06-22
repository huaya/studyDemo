package com.maxlong.study.zk.recipes.lock;

/**
 * 分布式悲观锁类型
 *
 * Created by IntelliJ IDEA.
 * User: ma.xl
 * Date: 2015/11/29
 * Time: 14:55
 * To change this template use File | Settings | File and Code Templates.
 */
public enum ZkDistributedLockType {

    SIMPLE,
    REENTRANT,
    READWRITE

}
