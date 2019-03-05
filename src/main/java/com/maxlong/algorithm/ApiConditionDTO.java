package com.maxlong.algorithm;

import java.io.Serializable;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-25 11:38
 */
public class ApiConditionDTO implements Serializable {

    private String allowSize;

    public String getAllowSize() {
        return allowSize;
    }

    public void setAllowSize(String allowSize) {
        this.allowSize = allowSize;
    }
}
