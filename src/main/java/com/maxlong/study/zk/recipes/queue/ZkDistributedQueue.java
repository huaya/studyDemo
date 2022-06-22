package com.maxlong.study.zk.recipes.queue;

import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * zk 分布式队列
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/19 14:54
 * abacus-parent
 */
public class ZkDistributedQueue<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZkDistributedQueue.class);

    private DistributedQueue<T> distributedQueue;

    public ZkDistributedQueue(CuratorFramework client,String queuePath,QueueConsumer<T> consumer,QueueSerializer<T> serializer,CuratorListener listener){
        client.getCuratorListenable().addListener(listener);
        client.start();
        QueueBuilder<T> builder = QueueBuilder.builder(client, consumer, serializer, queuePath);
        distributedQueue = builder.buildQueue();
    }


    /**
     * 启动队列
     *
     */
    public void start() throws Exception{
        try {
            distributedQueue.start();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 发布数据
     *
     * @param item
     */
    public void put(T item){
        try {
            this.distributedQueue.put(item);
        } catch (Exception e) {
	        LOGGER.error("put item unknown exception : {}", e);
        }
    }

    /**
     * 发布数据指定超时时间
     *
     * @param item
     * @param maxWait
     * @param timeUnit
     */
    public void put(T item,int maxWait,TimeUnit timeUnit){
        try {
            this.distributedQueue.put(item,maxWait,timeUnit);
        } catch (Exception e) {
	        LOGGER.error("put item unknown exception, maxWait {}, timeUnit {} : {}", maxWait, timeUnit, e);
        }
    }

    /**
     *
     * 关闭队列通道
     *
     */
    public void close(){
        CloseableUtils.closeQuietly(this.distributedQueue);
    }

    public static void main(String []args){

        CuratorFramework client = CuratorFrameworkFactory.createCuratorFrameworkManager().getCuratorFramework();

        String queuePath = "/queue";

        QueueConsumer<String> consumer = new QueueConsumer<String>() {
            @Override
            public void consumeMessage(String message) throws Exception {
                System.out.println("get message=>" + message);
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("newState=>" + newState.toString());
            }
        };

        QueueSerializer<String> serializer = new QueueSerializer<String>() {
            @Override
            public byte[] serialize(String item) {
                try {
                    return item.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return new byte[0];
            }

            @Override
            public String deserialize(byte[] bytes) {
                try {
                    return new String(bytes,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("e=>" + event);
            }
        };

        ZkDistributedQueue<String> distributedQueue = new ZkDistributedQueue<String>(client,queuePath,consumer,serializer,listener);

        try {
            distributedQueue.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


        distributedQueue.put("simple message");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        distributedQueue.close();

        try {
            Thread.sleep(10000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
