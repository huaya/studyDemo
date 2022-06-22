package com.maxlong.study.zk.recipes.queue;

import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedIdQueue;
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
 * 指定ID的分布式队列
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/19 15:37
 * abacus-parent
 */
public class ZkDistributedIdQueue<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZkDistributedIdQueue.class);

    private DistributedIdQueue<T> distributedIdQueue;

    public ZkDistributedIdQueue(CuratorFramework client,String queuePath,QueueConsumer<T> consumer,QueueSerializer<T> serializer,CuratorListener listener){
        client.getCuratorListenable().addListener(listener);
        client.start();
        QueueBuilder<T> builder = QueueBuilder.builder(client, consumer, serializer, queuePath);
        distributedIdQueue = builder.buildIdQueue();
    }


    /**
     * 启动队列
     *
     */
    public void start() throws Exception{
        try {
            distributedIdQueue.start();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 发布数据
     *
     * @param item
     * @param itemId
     */
    public void put(T item,String itemId){
        try {
            this.distributedIdQueue.put(item,itemId);
        } catch (Exception e) {
	        LOGGER.error("put item unknown exception, itemId {} : {}", itemId, e);
        }
    }

    /**
     * 发布数据指定超时时间
     *
     * @param item
     * @param maxWait
     * @param timeUnit
     */
    public void put(T item,String itemId,int maxWait,TimeUnit timeUnit){
        try {
            this.distributedIdQueue.put(item, itemId, maxWait, timeUnit);
        } catch (Exception e) {
	        LOGGER.error("put item unknown exception, itemId {}, maxWait {}, timeUnit {} : {}", itemId, maxWait, timeUnit, e);
        }
    }

    /**
     * 指定Id移除队列中数据
     *
     * @param itemId
     */
    public int remove(String itemId){
        try {
            return  this.distributedIdQueue.remove(itemId);
        } catch (Exception e) {
	        LOGGER.error("remove item unknown exception, itemId {} : {}", itemId, e);
        }
        return -1;
    }

    /**
     *
     * 关闭队列通道
     *
     */
    public void close(){
        CloseableUtils.closeQuietly(this.distributedIdQueue);
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

        ZkDistributedIdQueue<String> distributedQueue = new ZkDistributedIdQueue<String>(client,queuePath,consumer,serializer,listener);

        try {
            distributedQueue.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        distributedQueue.put("simple message","id0");

        try {
            Thread.sleep(20000);
            System.out.println("remove=>" + distributedQueue.remove("id0"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        try {
            Thread.sleep(10000);
            distributedQueue.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
