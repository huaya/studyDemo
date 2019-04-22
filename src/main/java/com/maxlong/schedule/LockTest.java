package com.maxlong.schedule;

import com.maxlong.study.utils.DateFormat;
import com.maxlong.study.utils.DateUtil;
import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import com.maxlong.study.zk.recipes.leader.LeaderLatchCandidater;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-4-18 18:46
 */
@Slf4j
public class LockTest {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.createCuratorFrameworkManager().getCuratorFramework();
        while (true){
            LeaderLatchCandidater candidater = new LeaderLatchCandidater(curatorFramework, "/maxlong", DateUtil.nowDateTime(DateFormat.STYLE13));
            candidater.requireElection();
            Thread.sleep(1000);
            if(candidater.isLeader()){
                log.info("----------------------------------");
            }
            candidater.close();
        }
    }
}
