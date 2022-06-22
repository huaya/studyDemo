package com.maxlong.study.zk.api;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.utils.CloseableUtils;
/**4
 * CuratorFramework 管理器
 * <p/>
 * Created by IntelliJ IDEA.
 * User: ma.xl
 * Date: 2015/11/28
 * Time: 17:42
 * To change this template use File | Settings | File and Code Templates.
 */
public class CuratorFrameworkManager {

    private CuratorFramework curatorFramework;


    /**
     * 初始化CuratorFramework
     *
     * @param connectionString
     * @param retryPolicy
     * @param namespace
     * @param connectionTimeoutMs
     * @param sessionTimeoutMs
     * @return
     */
    public void initCuratorFramework(String connectionString, RetryPolicy retryPolicy, String namespace, int connectionTimeoutMs, int sessionTimeoutMs) {
        curatorFramework = org.apache.curator.framework.CuratorFrameworkFactory.builder()
                .namespace(namespace)
                .connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs).build();
    }


    /**
     * 添加ConnectionStateListener
     */
    public void addConnectionStateListener(ConnectionStateListener connectionStateListener) {
        if (curatorFramework != null)
            curatorFramework.getConnectionStateListenable().addListener(connectionStateListener);
    }

    /**
     * 启动CuratorFramework
     */
    public void start() {
        if (curatorFramework != null) curatorFramework.start();
    }

    /**
     * 关闭CuratorFramework
     */
    public void close() {
        if (curatorFramework != null) CloseableUtils.closeQuietly(curatorFramework);
    }

    /**
     * 获取CuratorFramework
     *
     * @return
     */
    public CuratorFramework getCuratorFramework() {
        return this.curatorFramework;
    }
}
