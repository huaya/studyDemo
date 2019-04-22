package com.maxlong.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-12-20 15:30
 */
@Slf4j
@Component
public class ScheduledManage {

    @Autowired
    private ScheduledLeader scheduledLeader;

    /**
     * 现货报价数据整理
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void configPrictSortOut() throws Exception {
        if( scheduledLeader.isLeader()){
            Thread.sleep(1000);
            log.info("**************************************");
        }
        scheduledLeader.close();
    }

}
