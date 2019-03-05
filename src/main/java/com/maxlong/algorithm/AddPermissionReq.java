package com.maxlong.algorithm;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-27 11:18
 */
public class AddPermissionReq implements Serializable {

    private String name;

    private String remark;

    private String status;

    private List<ResourceRule> resourceRules;

    private List<RecordRule> recordRules;

    private List<ApiRule> apiRules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResourceRule> getResourceRules() {
        return resourceRules;
    }

    public void setResourceRules(List<ResourceRule> resourceRules) {
        this.resourceRules = resourceRules;
    }

    public List<RecordRule> getRecordRules() {
        return recordRules;
    }

    public void setRecordRules(List<RecordRule> recordRules) {
        this.recordRules = recordRules;
    }

    public List<ApiRule> getApiRules() {
        return apiRules;
    }

    public void setApiRules(List<ApiRule> apiRules) {
        this.apiRules = apiRules;
    }
}
