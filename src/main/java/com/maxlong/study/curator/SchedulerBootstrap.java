package com.maxlong.study.curator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:44
 * 类说明:
 */
public interface SchedulerBootstrap {


    void start() throws Exception;

    void stop() throws Exception;
}