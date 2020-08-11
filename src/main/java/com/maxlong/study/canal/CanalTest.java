package com.maxlong.study.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

/**
 * Created on 2020/8/4.
 *
 * @author MaXiaolong
 * @Email hu5624548@163.com
 */
public class CanalTest {

    private final static String zkHost= "172.21.1.38:2181,172.21.1.39:2181,172.21.1.40:2181/cloudcanal";
    private final static String destination = "opcloud-dev";
    private final static String db = "orderplus-db-dev";

    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newClusterConnector(zkHost, destination, null, null);
        System.out.println("feawrawrwa");
    }
}
