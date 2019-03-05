package com.maxlong.algorithm;

import java.io.Serializable;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-25 11:32
 */
public class RecordConditionDTO implements Serializable {

    /** 允许追溯的天数 */
    private String allowDays;

    /** 允许查看的开始日期（闭区间） */
    private String allowBegin;

    /** 允许查看的结束日期（闭区间） */
    private String allowEnd;

    public String getAllowDays() {
        return allowDays;
    }

    public void setAllowDays(String allowDays) {
        this.allowDays = allowDays;
    }

    public String getAllowBegin() {
        return allowBegin;
    }

    public void setAllowBegin(String allowBegin) {
        this.allowBegin = allowBegin;
    }

    public String getAllowEnd() {
        return allowEnd;
    }

    public void setAllowEnd(String allowEnd) {
        this.allowEnd = allowEnd;
    }
}
