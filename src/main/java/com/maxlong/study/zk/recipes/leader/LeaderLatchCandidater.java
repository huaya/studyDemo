package com.maxlong.study.zk.recipes.leader;

import com.maxlong.study.zk.recipes.leader.api.Candidater;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Latch模式候选人
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/17 16:39
 * abacus-parent
 */
public class LeaderLatchCandidater implements Candidater {

    /* 竞选者代理 */
    private LeaderLatch leaderLatch;

    private CuratorFramework client;

    /* 参入者节点 */
    private String nodeId;

    /**
     * 初始化
     *
     * @param client     客户端
     * @param leaderPath 选举投票箱
     * @param nodeId     选举节点
     */
    public LeaderLatchCandidater(CuratorFramework client, String leaderPath, String nodeId){
        this.leaderLatch = new LeaderLatch(client, leaderPath, "Candidater-" + nodeId);
        this.nodeId      = nodeId;
        this.client      = client;
        this.client.getConnectionStateListenable().addListener(new LeaderLatchCandidaterConnectionStateListener(this));
        if(!client.getState().equals(CuratorFrameworkState.STARTED)){
            this.client.start();
        }
    }

    public static class LeaderLatchCandidaterConnectionStateListener implements ConnectionStateListener{

        private LeaderLatchCandidater candidater ;
        public LeaderLatchCandidaterConnectionStateListener(LeaderLatchCandidater candidater){
            this.candidater = candidater;
        }

        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            if(newState == ConnectionState.LOST){

                System.out.println("connectionState lost");

            }
        }
    }

    /**
     * 参入选举
     *
     * @throws Exception
     */
    public void requireElection() throws Exception {
        if (this.leaderLatch != null && leaderLatch.getState() != LeaderLatch.State.STARTED)
            this.leaderLatch.start();
    }

    /**
     * 判断是否是Leader
     *
     * @return
     */
    public boolean isLeader(){
        return this.leaderLatch.hasLeadership();
    }

    /**
     * 获得Leader  Id
     *
     * @return
     */
    public String getLeaderId() throws Exception{
        return this.leaderLatch.getLeader().getId();
    }

    /**
     * 获取节点
     *
     * @return
     */
    public String getNodeId(){
        return this.nodeId;
    }

    /**
     * 等待选举结果(有结果立刻返回)
     *
     * @param timeout
     * @param unit
     * @return  true 领导 false 非领导
     * @throws InterruptedException
     */
    public boolean awaitElection(long timeout, TimeUnit unit) throws InterruptedException{
       return this.leaderLatch.await(30, TimeUnit.SECONDS);
    }

    /**
     * 释放参选资格关闭自己
     *
     * @throws IOException
     */
    public void close()throws IOException {
         this.leaderLatch.close();
    }

}
