package com.maxlong.study.zk.recipes.leader;

import com.maxlong.study.zk.recipes.leader.api.Candidater;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 选择模式候选人
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/17 18:22
 * abacus-parent
 */
public class SelectorCandidater extends LeaderSelectorListenerAdapter implements Closeable, Candidater {

    private LeaderSelector leaderSelector;

    private CuratorFramework client;

    /* 参入者节点 */
    private String nodeId;

    private LeaderProcesser leaderProcesser;

    /**
     *
     * @param client
     * @param path
     * @param nodeId
     * @param leaderProcesser
     */
    public SelectorCandidater(CuratorFramework client, String path, String nodeId,LeaderProcesser leaderProcesser){
        this.client = client;
        this.leaderSelector = new LeaderSelector(client, path, this);
        /* can be leader again */
        leaderSelector.autoRequeue();
        this.nodeId = nodeId;
        this.client.start();
        this.leaderProcesser = leaderProcesser;
    }

    /**
     * 如果该节点是领导时,该领导节点处理器
     */
    public interface LeaderProcesser {

        void process() throws InterruptedException;

    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        try{
            while (true){
                /* 支持打断模式，让该节点推出领导地位，触发再次竞选 */
                this.leaderProcesser.process();
            }
        }catch(InterruptedException e){
            throw  e;
        }
    }

    @Override
    public void requireElection() throws Exception {
        this.leaderSelector.start();
    }

    @Override
    public boolean isLeader() {
        return this.leaderSelector.hasLeadership();
    }

    @Override
    public String getLeaderId() throws Exception{
        return this.leaderSelector.getLeader().getId();
    }

    @Override
    public String getNodeId() {
        return this.nodeId;
    }

    @Override
    public boolean awaitElection(long timeout, TimeUnit unit) throws InterruptedException{
        long waitNanos = TimeUnit.NANOSECONDS.convert(timeout, unit);
        synchronized(this)
        {
            while ( (waitNanos > 0) &&  !isLeader() )
            {
                long startNanos = System.nanoTime();
                TimeUnit.NANOSECONDS.timedWait(this, waitNanos);
                long elapsed = System.nanoTime() - startNanos;
                waitNanos -= elapsed;
            }
        }
        return isLeader();
    }

    @Override
    public void close()  throws IOException{
        leaderSelector.close();
    }
}
