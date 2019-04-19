package com.maxlong.study.curator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:43
 * 类说明:
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接zookeeper服务器，leader选举
 * @author hao.wang 2017年1月18日 下午5:29:44
 *
 */
public class ZookeeperClusterManager implements ClusterManager{

    private CuratorFramework framework;

    private LeaderLatch leaderLatch;

    private static List<ClusterLeaderListener> listeners = new ArrayList<ClusterLeaderListener>();

    private volatile boolean isLeaderLatchStart = false;

    private static final String DETAULE_ELECTION_PATH = "/election/demo/hao";


    public ZookeeperClusterManager() {
        framework= CuratorFrameworkFactory.newClient("192.168.122.128:2181",30000,5000,
                new ExponentialBackoffRetry(2000,Integer.MAX_VALUE));
        framework.start();
        leaderLatch = new LeaderLatch(framework, DETAULE_ELECTION_PATH);
        try {
            leaderLatch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isLeaderLatchStart = true;
        leaderLatch.addListener(new LeaderLatchListener() {

            @Override
            public void notLeader() {
                synchronized(this){
                    if(!listeners.isEmpty()){
                        for (ClusterLeaderListener listener : listeners) {
                            listener.loserLeader();
                        }
                    }
                }
            }

            @Override
            public void isLeader() {
                synchronized(this){
                    if(!listeners.isEmpty()){
                        for (ClusterLeaderListener listener : listeners) {
                            listener.gainLeader();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void acquireLeader() throws Exception {
        this.leaderLatch.await();
    }

    @Override
    public void closeLeaderLatch() throws Exception {
        this.leaderLatch.close();

    }

    @Override
    public void startLeaderLatch() throws Exception {
        if(!isLeaderLatchStart){
            this.leaderLatch.start();
        }


    }

    @Override
    public void addLeaderListener(ClusterLeaderListener listener) throws Exception {
        if(!this.listeners.contains(listener)){
            listeners.add(listener);
        }
    }

}
