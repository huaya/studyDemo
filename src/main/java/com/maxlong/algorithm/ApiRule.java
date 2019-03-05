package com.maxlong.algorithm;

import java.io.Serializable;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-27 11:23
 */
public class ApiRule implements Serializable {

    private String apiId;

    private String allow;

    private ApiConditionDTO condition;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public ApiConditionDTO getCondition() {
        return condition;
    }

    public void setCondition(ApiConditionDTO condition) {
        this.condition = condition;
    }
}
