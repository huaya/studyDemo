package com.maxlong.study.future;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-02-24 0024 13:52
 * 类说明
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String parm) {

        StringBuffer sb = new StringBuffer();
        try {
            for(int i=0;i<10;i++){
                sb.append(parm);

                    Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
