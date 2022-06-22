package com.maxlong.study.zk.recipes.queue;

import com.maxlong.study.zk.api.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 简单队列
 *
 * @author ma.xl
 * @version 1.0.0
 * @since 2015/6/19 17:20
 * abacus-parent
 */
public class  ZkSimpleDistributedQueue {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZkSimpleDistributedQueue.class);

   private  SimpleDistributedQueue simpleDistributedQueue;

    public ZkSimpleDistributedQueue(CuratorFramework client,String queuePath,CuratorListener listener){
        client.getCuratorListenable().addListener(listener);
        client.start();
        simpleDistributedQueue = new SimpleDistributedQueue(client,queuePath);
    }

    /**
     * 入队数据
     *
     * @param data
     * @return
     */
    public boolean offer(byte[] data){
        try {
            return this.simpleDistributedQueue.offer(data);
        } catch (Exception e) {
	        LOGGER.warn("offer unknown exception : {}", e);
        }
        return false;
    }

    /**
     * Attempts to remove the head of the queue and return it. Returns null if the queue is empty.
     *
     * @return Head of the queue or null.
     */
    public byte[] poll(){
        try {
            return this.simpleDistributedQueue.poll();
        } catch (Exception e) {
	        LOGGER.error("poll unknown exception : {}", e);
        }
        return null;
    }

    /**
     * Retrieves and removes the head of this queue, waiting up to the
     * specified wait time if necessary for an element to become available.
     *
     * @param timeout how long to wait before giving up, in units of
     *        <tt>unit</tt>
     * @param timeUnit a <tt>TimeUnit</tt> determining how to interpret the
     *        <tt>timeout</tt> parameter
     * @return the head of this queue, or <tt>null</tt> if the
     *         specified waiting time elapses before an element is available
     */
    public byte[] poll(long timeout,TimeUnit timeUnit){
        try {
            return this.simpleDistributedQueue.poll(timeout,timeUnit);
        } catch (Exception e) {
	        LOGGER.error("poll unknown exception : {}", e);
        }
        return null;
    }

    /**
     * Attempts to remove the head of the queue and return it.
     *
     * @return The former head of the queue
     */
    public byte[] remove(){
        try {
            return this.simpleDistributedQueue.remove();
        } catch (Exception e) {
	        LOGGER.error("remove unknown exception : {}", e);
        }
        return null;
    }

    /**
     * Removes the head of the queue and returns it, blocks until it succeeds.
     *
     * @return The former head of the queue
     * @throws Exception errors
     */
    public byte[] take(){
        try {
            return this.simpleDistributedQueue.take();
        } catch (Exception e) {
	        LOGGER.error("take unknown exception : {}", e);
        }
        return null;
    }

    /**
     * Returns the data at the first element of the queue, or null if the queue is empty.
     *
     * @return data at the first element of the queue, or null.
     * @throws Exception errors
     */
    public byte[] peek(){
        try {
            return this.simpleDistributedQueue.peek();
        } catch (Exception e) {
	        LOGGER.error("peek unknown exception : {}", e);
        }
        return null;
    }

    /**
     * Return the head of the queue without modifying the queue.
     *
     * @return the data at the head of the queue.
     */
    public byte[] element(){
        try {
            return this.simpleDistributedQueue.element();
        } catch (Exception e) {
	        LOGGER.error("element unknown exception : {}", e);
        }
        return null;
    }



    public static void main(String []args){

        CuratorFramework client = CuratorFrameworkFactory.createCuratorFrameworkManager().getCuratorFramework();

        String queuePath = "/queue";

        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("e=>" + event);
            }
        };

        ZkSimpleDistributedQueue queue = new ZkSimpleDistributedQueue(client,queuePath,listener);

        queue.offer("test".getBytes());


        System.out.println("take=>"+ new String(queue.take()));

    }
}
