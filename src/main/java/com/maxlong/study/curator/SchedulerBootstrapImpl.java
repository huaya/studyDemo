package com.maxlong.study.curator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:44
 * 类说明:
 */
public class SchedulerBootstrapImpl implements SchedulerBootstrap {

    private String serverName;

    private ClusterManager clusterManager = new ZookeeperClusterManager();

    private volatile boolean shutDown = false;

    public SchedulerBootstrapImpl(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public void start() throws Exception {
        if (shutDown) {
            throw new RuntimeException("任务已经停止");
        }
        boolean leaderAcuqire = false;
        // 这个过程就是获取leader权限
        while (!Thread.currentThread().isInterrupted()) {
            try {
                clusterManager.acquireLeader();
                leaderAcuqire = true;
                break;
            } catch (Exception e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                    return;
                }
                if (shutDown) {
                    return;
                }
                Thread.sleep(5000);
            }
        }
        System.out.println(serverName + "获得处理权力，开始执行任务");
        if (leaderAcuqire) {
            startService();
        }

        clusterManager.addLeaderListener(new ClusterLeaderListener() {

            @Override
            public void gainLeader() {
                // 获得leader权力，执行任务
                startService();
            }

            @Override
            public void loserLeader() {
                // 丢失leader权力，关闭任务
                destoryService();
            }

        });
    }

    @Override
    public void stop() throws Exception {
        destoryService();
        shutDown = true;
    }

    private void startService() {
        SchedulerService.startTimer(serverName);
        try {
            clusterManager.startLeaderLatch();
        } catch (Exception e) {
            SchedulerService.stopTimer(serverName);
            try {
                clusterManager.closeLeaderLatch();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void destoryService() {
        SchedulerService.stopTimer(serverName);
        try {
            clusterManager.closeLeaderLatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}