package com.maxlong.study.kafka;

import java.io.Serializable;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-11-21 14:45
 */
public class SpotPriceNoiceMsg implements Serializable {

    private String cfgId;

    private String low;

    private String high;

    private String middle;

    private String publishDate;

    private String publishPoint;

    private String publishTime;

    private String contract;

    public String getCfgId() {
        return cfgId;
    }

    public void setCfgId(String cfgId) {
        this.cfgId = cfgId;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishPoint() {
        return publishPoint;
    }

    public void setPublishPoint(String publishPoint) {
        this.publishPoint = publishPoint;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
