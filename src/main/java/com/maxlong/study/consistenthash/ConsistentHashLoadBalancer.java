package com.maxlong.study.consistenthash;


import com.maxlong.study.hash.HashStrategy;
import com.maxlong.study.hash.impl.FnvHashStrategy;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {

    private HashStrategy hashStrategy = new FnvHashStrategy();

    private final TreeMap<Integer, Server> ring = new TreeMap<>();

    private final static int VIRTUAL_NODE_SIZE = 10;

    private final static String VIRTUAL_NODE_SUFFIX = "&&";

    private volatile boolean init = false;

    public ConsistentHashLoadBalancer(List<Server> servers) {
        buildConsistentHashRing(servers);
        this.init = true;
    }

    public Server select(Invocation invocation) {
        if (!init) {
            throw new Error("no server has inited");
        }
        int invocationHashCode = hashStrategy.getHashCode(invocation.getHashKey());
        return locate(invocationHashCode);
    }

    private Server locate(int invocationHashCode) {
        // 向右找到第一个 key
        Map.Entry<Integer, Server> locateEntry = this.ring.ceilingEntry(invocationHashCode);
        if (locateEntry == null) {
            // 想象成一个环，超过尾部则取第一个 key
            locateEntry = ring.firstEntry();
        }
        return locateEntry.getValue();
    }

    private void buildConsistentHashRing(List<Server> servers) {
        for (Server server : servers) {
            for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
                // 新增虚拟节点的方式如果有影响，也可以抽象出一个由物理节点扩展虚拟节点的类
                this.ring.put(hashStrategy.getHashCode(server.getUrl() + VIRTUAL_NODE_SUFFIX + i), server);
            }
        }
    }

}
