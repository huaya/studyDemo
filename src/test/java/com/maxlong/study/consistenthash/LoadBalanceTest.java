package com.maxlong.study.consistenthash;

import com.google.common.util.concurrent.AtomicLongMap;
import com.maxlong.study.utils.FileUtil;
import com.maxlong.study.utils.StatisticsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class LoadBalanceTest {

    static String[] ips = new String[]{};

    static {
        List<String> ipsList = FileUtil.readLineFromFile("D:\\workspace-mxl\\studyDemo\\src\\main\\resources\\json\\ips.text", 1, "UTF-8");
        ips = ipsList.toArray(ips);
    }

    /**
     * 测试分布的离散情况
     */
    @Test
    public void testDistribution() {
        StopWatch watch = new StopWatch();
        watch.start("监控");
        List<Server> servers = new ArrayList<>();
        for (String ip : ips) {
            servers.add(new Server(ip+":8080"));
        }
        LoadBalancer chloadBalance = new ConsistentHashLoadBalancer(servers);
        // 构造 10000 随机请求
        List<Invocation> invocations = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            invocations.add(new Invocation(UUID.randomUUID().toString()));
        }
        // 统计分布
        AtomicLongMap<Server> atomicLongMap = AtomicLongMap.create();
        for (Server server : servers) {
            atomicLongMap.put(server, 0);
        }
        for (Invocation invocation : invocations) {
            Server selectedServer = chloadBalance.select(invocation);
            atomicLongMap.getAndIncrement(selectedServer);
        }
        watch.stop();
        System.out.println(StatisticsUtil.variance(atomicLongMap.asMap().values().toArray(new Long[]{})));
        System.out.println(StatisticsUtil.standardDeviation(atomicLongMap.asMap().values().toArray(new Long[]{})));
        System.out.println(watch.getLastTaskTimeMillis());
    }

    /**
     * 测试节点新增删除后的变化程度
     */
    @Test
    public void testNodeAddAndRemove() {
        StopWatch watch = new StopWatch();
        watch.start("监控");
        List<Server> servers = new ArrayList<>();
        for (String ip : ips) {
            servers.add(new Server(ip));
        }
        List<Server> serverChanged = servers.subList(0, 80);
        LoadBalancer chloadBalance = new ConsistentHashLoadBalancer(servers);
        // 构造 10000 随机请求
        List<Invocation> invocations = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            invocations.add(new Invocation(UUID.randomUUID().toString()));
        }
        int count = 0;
        for (Invocation invocation : invocations) {
            Server origin = chloadBalance.select(invocation);
            Server changed = chloadBalance.select(invocation);
            if (origin.getUrl().equals(changed.getUrl())) count++;
        }
        watch.stop();
        System.out.println(count / 10000D);
        System.out.println(watch.getLastTaskTimeMillis());
    }
}
