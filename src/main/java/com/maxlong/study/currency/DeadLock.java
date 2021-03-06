package com.maxlong.study.currency;

/**
 * 避免死锁的几种方式：
 *  1、注意加锁顺序
 *  2、使用Lock加锁，可以自主控制加锁、解锁
 *  3、死锁检测(过于复杂且不可靠)
 * Created by IntelliJ IDEA.
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/16 21:42
 * 类说明:
 */
public class DeadLock {

    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (o1){

                System.out.println("【thread1】get lock o1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2){
                    System.out.println("【thread1】get lock o2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o2){

                System.out.println("【thread2】get lock o2");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1){
                    System.out.println("【thread2】get lock o1");
                }
            }
        }).start();

    }


}

