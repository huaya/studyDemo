package com.maxlong.study.consistenthash;

import com.google.common.util.concurrent.AtomicLongMap;
import com.maxlong.study.utils.FileUtil;
import com.maxlong.study.utils.StatisticsUtil;
import org.junit.jupiter.api.Test;
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
        List<Server> servers = new ArrayList<>();
        for (String ip : ips) {
            servers.add(new Server(ip+":8080"));
        }
        LoadBalancer chloadBalance = new ConsistentHashLoadBalancer();
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
            Server selectedServer = chloadBalance.select(servers, invocation);
            atomicLongMap.getAndIncrement(selectedServer);
        }
        System.out.println(StatisticsUtil.variance(atomicLongMap.asMap().values().toArray(new Long[]{})));
        System.out.println(StatisticsUtil.standardDeviation(atomicLongMap.asMap().values().toArray(new Long[]{})));
    }

    /**
     * 测试节点新增删除后的变化程度
     */
    @Test
    public void testNodeAddAndRemove() {
        List<Server> servers = new ArrayList<>();
        for (String ip : ips) {
            servers.add(new Server(ip));
        }
        List<Server> serverChanged = servers.subList(0, 80);
        LoadBalancer chloadBalance = new ConsistentHashLoadBalancer();
        // 构造 10000 随机请求
        List<Invocation> invocations = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            invocations.add(new Invocation(UUID.randomUUID().toString()));
        }
        int count = 0;
        for (Invocation invocation : invocations) {
            Server origin = chloadBalance.select(servers, invocation);
            Server changed = chloadBalance.select(serverChanged, invocation);
            if (origin.getUrl().equals(changed.getUrl())) count++;
        }
        System.out.println(count / 10000D);
    }
}
