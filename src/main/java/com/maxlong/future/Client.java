package com.maxlong.future;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-04-11 0011 14:17
 * 类说明
 */
public class Client {

    public FutureData request(final String name){

        final FutureData futureData = new FutureData();

        new Thread(() -> {
            RealData realData = new RealData(name);
            futureData.setRealData(realData);
        }).start();

        return futureData;
    }

}
