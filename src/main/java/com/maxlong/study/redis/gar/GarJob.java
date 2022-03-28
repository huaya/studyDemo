package com.maxlong.study.redis.gar;

import com.maxlong.study.redis.JobExecute;
import lombok.AllArgsConstructor;

import java.util.Set;

/**
 * Created on 2022/3/25.
 *
 * @author xxxx
 * @Email xxxx
 */
@AllArgsConstructor
public abstract class GarJob implements Runnable {

    protected String waitKey;

    protected String onKey;

    abstract Set<Long> getAndMove() throws Exception;

    @Override
    public void run() {
        while (true) {
            try {
                Set<Long> keys = getAndMove();
                for (Long key : keys) {
                    if(!JobExecute.synSet.add(key)){
                        System.out.println(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
