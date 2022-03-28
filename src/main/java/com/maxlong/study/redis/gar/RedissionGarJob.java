package com.maxlong.study.redis.gar;

import com.maxlong.study.redis.RedssionClientHolder;
import com.maxlong.study.redis.gar.GarJob;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.redisson.api.*;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created on 2022/3/25.
 *
 * @author xxxx
 * @Email xxxx
 */
public class RedissionGarJob extends GarJob {

    public RedissionGarJob(String waitKey, String onKey) {
        super(waitKey, onKey);
    }

    public Set<Long> getAndMove() throws Exception {
        RedissonClient redisson = RedssionClientHolder.redisson;
        RLock rLock = redisson.getLock("get_and_move_lock");
        try {
            rLock.lock(15, TimeUnit.SECONDS);

            Collection<Object> members = redisson.getScoredSortedSet(waitKey).valueRange(0, 5);

            Thread.sleep(RandomUtils.nextInt(100, 5000));

            redisson.getSet(onKey).addAll(members);
            redisson.getScoredSortedSet(waitKey).removeAll(members);
            return members.stream()
                    .map(Object::toString)
                    .filter(item -> StringUtils.isNotBlank(item) && NumberUtils.isDigits(item))
                    .map(Long::valueOf).collect(Collectors.toSet());
        } finally {
            rLock.unlock();
        }
    }

}
