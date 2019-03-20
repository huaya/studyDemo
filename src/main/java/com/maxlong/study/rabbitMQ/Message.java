package com.maxlong.study.rabbitMQ;

import java.io.Serializable;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-04-10 0010 10:41
 * 类说明
 */
public class Message implements Serializable {

    private String msgId;

    private String msgBody;

    public Message() {
    }

    public Message(String msgId, String msgBody) {
        this.msgId = msgId;
        this.msgBody = msgBody;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
}
