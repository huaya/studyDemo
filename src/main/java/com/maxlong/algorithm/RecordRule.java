package com.maxlong.algorithm;

import java.io.Serializable;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-27 11:22
 */
public class RecordRule implements Serializable {

    private String recordId;

    private String allow;

    private RecordConditionDTO condition;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public RecordConditionDTO getCondition() {
        return condition;
    }

    public void setCondition(RecordConditionDTO condition) {
        this.condition = condition;
    }
}
