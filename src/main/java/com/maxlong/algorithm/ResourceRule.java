package com.maxlong.algorithm;

import java.io.Serializable;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-27 11:19
 */
public class ResourceRule implements Serializable {

    private String resourceId;

    private String allow;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }
}
