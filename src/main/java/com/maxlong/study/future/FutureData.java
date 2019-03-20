package com.maxlong.study.future;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-02-24 0024 13:56
 * 类说明
 */
public class FutureData  implements Data{

    protected RealData realData = null;

    protected boolean isReady = false;

    @Override
    public synchronized String getResult() {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }

        this.realData = realData;
        this.isReady = true;
        notifyAll();
        return;
    }

}
