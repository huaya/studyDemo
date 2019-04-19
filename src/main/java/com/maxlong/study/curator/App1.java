package com.maxlong.study.curator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:45
 * 类说明:
 */
public class App1 {
    public static void main(String[] args) {
        SchedulerBootstrap sb = new SchedulerBootstrapImpl("服务1");
        try {
            sb.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
