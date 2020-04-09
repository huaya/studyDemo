package com.maxlong.schedule;

import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import com.maxlong.study.zk.recipes.leader.LeaderLatchCandidater;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-4-19 16:05
 */
@Component
public class ScheduledLeader {


    static LeaderLatchCandidater leaderLatchCandidater;

    static {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.createCuratorFrameworkManager().getCuratorFramework();
        leaderLatchCandidater = new LeaderLatchCandidater(curatorFramework, "/maxlong", "11111");
    }

    public ScheduledLeader() throws Exception {
//        leaderLatchCandidater.requireElection();
    }

    public boolean isLeader() throws Exception {
        leaderLatchCandidater.requireElection();
        Thread.sleep(1000);
        return leaderLatchCandidater.isLeader();
    }

    public void close() throws IOException {
        leaderLatchCandidater.close();
    }
}
