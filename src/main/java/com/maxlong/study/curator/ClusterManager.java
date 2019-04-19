package com.maxlong.study.curator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:43
 * 类说明:
 */
public interface ClusterManager {

    /**
     * 获取leader节点
     * @throws Exception
     */
    void acquireLeader() throws Exception;

    /**
     * 关闭leader选举
     * @throws Exception
     */
    void closeLeaderLatch() throws Exception;

    /**
     * 开始leader选举
     * @throws Exception
     */
    void startLeaderLatch() throws Exception;

    /**
     * 添加监听器
     * @throws Exception
     */
    void addLeaderListener(ClusterLeaderListener listener) throws Exception;
}
