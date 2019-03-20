package com.maxlong.study.future;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-04-11 0011 14:17
 * 类说明
 */
public class FutureTest {

    public static void main(String[] args) {

        Client client = new Client();

        FutureData realData = client.request("name");
        System.out.println("请求完毕！");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("请求结果：" + realData.getResult());
    }
}
