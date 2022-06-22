package com.maxlong.study.zk.recipes.queue;

import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedPriorityQueue;
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
 * 优先级队列
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/19 16:58
 * abacus-parent
 */
public class ZkDistributedPriorityQueue<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZkDistributedPriorityQueue.class);

    private DistributedPriorityQueue<T> distributedPriorityQueue;

    public ZkDistributedPriorityQueue(CuratorFramework client,String queuePath,QueueConsumer<T> consumer,QueueSerializer<T> serializer,CuratorListener listener){
        client.getCuratorListenable().addListener(listener);
        client.start();
        QueueBuilder<T> builder = QueueBuilder.builder(client, consumer, serializer, queuePath);
        distributedPriorityQueue = builder.buildPriorityQueue(0);
    }


    /**
     * 启动队列
     *
     */
    public void start() throws Exception{
        try {
            distributedPriorityQueue.start();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 发布数据
     *
     * @param item
     * @param priority 优先级 值越小优先级越高
     */
    public void put(T item,int priority){
        try {
            this.distributedPriorityQueue.put(item,priority);
        } catch (Exception e) {
	        LOGGER.error("put item unknown exception : {}", e);
        }
    }

    /**
     * 发布数据指定超时时间
     *
     * @param item
     * @param priority 优先级 值越小优先级越高
     * @param maxWait
     * @param timeUnit
     */
    public void put(T item,int priority,int maxWait,TimeUnit timeUnit){
        try {
            this.distributedPriorityQueue.put(item, priority, maxWait, timeUnit);
        } catch (Exception e) {
	        LOGGER.error("put item unknown exception, priority {}, maxWait {}, timeUnit {} : {}", priority, maxWait, timeUnit, e);
        }
    }

    /**
     *
     * 关闭队列通道
     *
     */
    public void close(){
        CloseableUtils.closeQuietly(this.distributedPriorityQueue);
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

        ZkDistributedPriorityQueue<String> distributedQueue = new ZkDistributedPriorityQueue<String>(client,queuePath,consumer,serializer,listener);

        try {
            distributedQueue.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        distributedQueue.put("simple message1",1);
        distributedQueue.put("simple message0",0);

        try {
            Thread.sleep(10000);
            distributedQueue.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
