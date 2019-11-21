package com.maxlong.study.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiMessageCorpconversationGetsendprogressRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiMessageCorpconversationGetsendprogressResponse;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;

import java.util.ArrayList;
import java.util.List;
import static com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request.Form;

/**
 * Created on 2019/11/14.
 *
 * @author MaXiaolong
 */
public class DingdingUser {

    private static final String agentId = "317088643";

    private static final String appId = "dingcx2hymkynwylgzyd";

    private static final String appSecret = "voPxPMDDBGrudrgershkGtJQM1F4RoCo4FnDKBaxXZlhcBwEQmYXpQKpRBqJcEMF";

    public static void main(String[] args) throws ApiException {
        String accessToken = getAccessToken();
        long taskId = sendMessage(accessToken, "275406665838761686", "oa");
        getsendprogress(taskId, accessToken);
    }

    private static String getAccessToken() throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appId);
        request.setAppsecret(appSecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        return response.getAccessToken();
    }

    private static Long sendMessage(String accessToken, String userList, String type) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(userList);
        request.setAgentId(Long.valueOf(agentId));
        request.setToAllUser(false);
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        switch (type) {
            case "text":
                msg.setMsgtype("text");
                msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
                msg.getText().setContent("您的产品在该站点明天将预售下架，请确保明天的全网销量达到指标\nPUBDSDS（100）站点");
                break;
            case "image":
                msg.setMsgtype("image");
                msg.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
                msg.getImage().setMediaId("@lADOdvRYes0CbM0CbA");
                request.setMsg(msg);
                break;
            case "file":
                msg.setMsgtype("file");
                msg.setFile(new OapiMessageCorpconversationAsyncsendV2Request.File());
                msg.getFile().setMediaId("@lADOdvRYes0CbM0CbA");
                break;
            case "link":
                msg.setMsgtype("link");
                msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
                msg.getLink().setTitle("test");
                msg.getLink().setText("test");
                msg.getLink().setMessageUrl("test");
                msg.getLink().setPicUrl("test");
                break;
            case "markdown":
                msg.setMsgtype("markdown");
                msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
                msg.getMarkdown().setText("##### test33");
                msg.getMarkdown().setTitle("### Title333");
                break;
            case "oa":
                msg.setMsgtype("oa");
                msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
                msg.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
                msg.getOa().getHead().setText("head");
                msg.getOa().getHead().setBgcolor("FFBBBBBB");

                msg.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
                Form productNo = new Form();
                productNo.setKey("产品编号：");
                productNo.setValue("PUBDSDS");
                Form salesVolume = new Form();
                salesVolume.setKey("销量：");
                salesVolume.setValue("200");
                Form siteName = new Form();
                siteName.setKey("站点名：");
                siteName.setValue("Cometgarden");
                msg.getOa().getBody().setForm(Lists.newArrayList(productNo, salesVolume, siteName));

                msg.getOa().getBody().setContent("您有产品明天将预售下架，请确保明天的全网销量达到指标。");
                break;
            case "action_card":
                msg.setMsgtype("action_card");
                msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
                msg.getActionCard().setTitle("xxx123411111");
                msg.getActionCard().setMarkdown("### 测试123111");
                msg.getActionCard().setSingleTitle("测试测试");
                msg.getActionCard().setSingleUrl("https://www.baidu.com");
                break;
        }

        request.setMsg(msg);
        OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, accessToken);
        return response.isSuccess() ? response.getTaskId() : null;
    }

    private static void getsendprogress(Long taskId, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/getsendprogress");
        OapiMessageCorpconversationGetsendprogressRequest request = new OapiMessageCorpconversationGetsendprogressRequest();
        request.setAgentId(Long.valueOf(agentId));
        request.setTaskId(taskId);
        OapiMessageCorpconversationGetsendprogressResponse response = client.execute(request, accessToken);
        if (response.isSuccess()) {
            Long status = response.getProgress().getStatus();
            Long percent = response.getProgress().getProgressInPercent();
            System.out.printf("状态：%s, 处理进度：%s", status, percent);
        } else {
            System.out.printf("处理失败, errMsg: %s", response.getErrmsg());
        }
        System.out.println();
    }

    enum Progress {
        //0=未开始，1=处理中，2=处理完毕
        S_0(0L, "未开始"),
        S_1(1L, "处理中"),
        S_2(2L, "处理完毕");

        private Long status;

        private String desc;

        Progress(Long status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public Long getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }
}
