package com.maxlong.enums;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年10月11日 下午3:35:06
 * 类说明
 */
public enum BusiType {
    STAND_DPAY("010001", "标准支付"),
    COLLECT_PAY("010002", "收款");

    String code;
    String name;

    BusiType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
 