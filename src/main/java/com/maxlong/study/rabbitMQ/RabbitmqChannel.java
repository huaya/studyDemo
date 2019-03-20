package com.maxlong.study.rabbitMQ;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j2;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月27日 下午5:41:54
 * 类说明
 */
@Log4j2
public class RabbitmqChannel implements Closeable {

    private static String DEFAULT_LOCAL_HOST = "127.0.0.1";

    private static int DEFAULT_PORT = 5672;

    public static final String DEFAULT_USER = "guest";

    public static final String DEFAULT_PASS = "guest";

    public static final String DEFAULT_VHOST = "/";

    /**
     * 默认重试次数
     */
    private static final int DEFAULT_RETRY_COUNT = 3;

    /**
     * 允许最大重试次数
     */
    private static final int DEFAULT_MAX_RETRY_COUNT = 5;

    /**
     * 默认重试时间间隔
     */
    private static final int DEFAULT_RETRY_INTERVAL_MS = 5000;

    /**
     * 允许最大重试时间间隔
     */
    private static final int DEFAULT_MAX_RETRY_INTERVAL_MS = 10000;

    /**
     * 队列主机
     */
    private String host;

    /**
     * 队列端口
     */
    private int port;

    /**
     * 虚拟主机
     */
    private String virtualHost = DEFAULT_VHOST;

    /**
     * 登录用户名
     */
    private String userName = DEFAULT_USER;

    /**
     * 登录密码
     */
    private String password = DEFAULT_PASS;

    private Connection connection;

    private Channel channel;

    private volatile boolean closed;

    /**
     * 连接重试的次数
     */
    private AtomicInteger retryConnectCount = new AtomicInteger(DEFAULT_RETRY_COUNT);

    /**
     * 连接重试的时间间隔
     */
    private int retryConnectInterval = DEFAULT_RETRY_INTERVAL_MS;

    public RabbitmqChannel(String host, int port, String userName, String password, String vhost, int retryConnectCount, int retryConnectInterval) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.virtualHost = vhost;
        if (retryConnectCount > 0 && retryConnectCount <= DEFAULT_MAX_RETRY_COUNT && retryConnectInterval > 0 && retryConnectInterval <= DEFAULT_MAX_RETRY_INTERVAL_MS) {
            this.retryConnectCount = new AtomicInteger(retryConnectCount);
            this.retryConnectInterval = retryConnectInterval;
        } else {
            //use default
        }
    }

    private void setClosed(boolean b) {
        this.closed=b;
    }

    private boolean createChannel() {
        boolean success = false;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);
        factory.setPort(this.port);
        factory.setVirtualHost(this.virtualHost);
        factory.setUsername(this.userName);
        factory.setPassword(this.password);

        /** 标记重试次数 */
        int retryCount = 0;
        /** 重试次数不包括建立连接的第一次 */
        while (this.retryConnectCount.getAndDecrement() + 1 > 0) {
            try {
                this.connection = factory.newConnection();
                this.channel = connection.createChannel();
                success = true;
                break;
            } catch (Exception e) {
                if (retryConnectCount.get() + 1 > 0) {
                    retryCount++;
                    log.info("create channel failed, will retry {} times", retryCount);
                    try {
                        Thread.sleep(retryConnectInterval);
                    } catch (InterruptedException ie) {
                        log.warn("retry sleep thread interrupted :{}", ie.getMessage());
                    }
                }else {
                    throw new RuntimeException("retry failed exception",e);
                }
            }
        }
        return success;
    }

    @Override
    public void close() throws IOException {

    }

}
 