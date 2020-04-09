package com.maxlong.study.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.maxlong.study.redis.RedisPool;
import com.maxlong.study.utils.StringUtil;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


/**
 * Created on 2019/11/14.
 *
 * @author MaXiaolong
 */
public class DingdingUser {

    private static final String agentId = "294857517";

    private static final String appId = "dingmnqfaiagwzuepve8";

    private static final String appSecret = "5v6Oy5VKirxBQJRacGtGzj_58N9rJPDQYIxSBT1XJjTQsmAnx7UMt521dIw5e7Xj";

    private static final String accessToken_L = "16e28aa7c0fe3cefb9414b825bf30fb9";

    public static void main(String[] args) throws ApiException {
        long taskId = sendMessage(getAccessToken(), "080726172337700948,275406665838761686", "markdown");
//        getsendprogress(taskId, accessToken_L);
    }

    public static String getAccessToken() throws ApiException {
        String accessToken = RedisPool.get("accessToken");
        if(StringUtils.isNoneBlank(accessToken)) {
            return accessToken;
        }
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appId);
        request.setAppsecret(appSecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        RedisPool.setEx("accessToken", response.getAccessToken(), 7200);
        return response.getAccessToken();
    }

    public static List<OapiUserSimplelistResponse.Userlist> getSimplelist(String accessToken, Long departmentId ) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(departmentId);
        request.setOffset(0L);
        request.setSize(10L);
        request.setHttpMethod("GET");
        OapiUserSimplelistResponse response = client.execute(request, accessToken);
        return response.getUserlist();
    }

    public static String getUser(String accessToken, String userId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");
        OapiUserGetResponse response = client.execute(request, accessToken);
        return response.getName();
    }

    public static List<OapiDepartmentListResponse.Department> getDepartment(String accessToken, String id) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(id);
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, accessToken);

        return response.getDepartment();
    }

    public static List<OapiUserGetAdminResponse.AdminList> getAdmin(String accessToken) throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get_admin");
        OapiUserGetAdminRequest request = new OapiUserGetAdminRequest();
        request.setHttpMethod("GET");

        OapiUserGetAdminResponse response = client.execute(request, accessToken);

        return response.getAdminList();
    }


    public static Long sendMessage(String accessToken, String userList, String type) throws ApiException {
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
                String url = "dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fwww.baidu.com&pc_slide=true";
//                String url = "dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fwww.baidu.com&pc_slide=false";

                msg.setMsgtype("link");
                msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
                msg.getLink().setTitle("link");
                msg.getLink().setText("link");
                msg.getLink().setMessageUrl(url);
                msg.getLink().setPicUrl("link");
                break;
            case "markdown":
                msg.setMsgtype("markdown");
                msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
                StringBuilder builderM = new StringBuilder();
                builderM.append("[Opstores](dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fopstores.orderplus.com&pc_slide=true) \n ");
                builderM.append(" \n 您有一条新的[开户申请](dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fopstores.orderplus.com&pc_slide=true)待处理，单号444，请登录Opstores查看并操作");
                msg.getMarkdown().setText(builderM.toString());
                msg.getMarkdown().setTitle("opstores");
                break;
            case "oa":
                msg.setMsgtype("oa");
                msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
                msg.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
                msg.getOa().getHead().setBgcolor("FF3333CC");
                msg.getOa().setPcMessageUrl("www.baidu.com");
                msg.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());

//                Form productNo = new Form();
//                productNo.setKey("产品编号：");
//                productNo.setValue("PUBDSDS");
//                Form salesVolume = new Form();
//                salesVolume.setKey("销量：");
//                salesVolume.setValue("200");
//                Form siteName = new Form();
//                siteName.setKey("站点名：");
//                siteName.setValue("Cometgarden");
//                msg.getOa().getBody().setForm(Lists.newArrayList(productNo, salesVolume, siteName));

                msg.getOa().getBody().setTitle("产品预售下架通知");
                StringBuilder builder = new StringBuilder();
                builder.append("您有产品明天即将预售下架，请确保明天的全网销量达到指标。\n");
                String content = "%s(%s)\t\t%s\n";
                builder.append(String.format(content, "PUBDSDS", "200", "Cometgarden"));
                builder.append(String.format(content, "PUBDSDS2", "200", "Cometgarden2"));
                builder.append(String.format(content, "PUBDSDS2", "200", "Cometgarden2"));
                builder.append(String.format(content, "PUBDSDS2", "200", "Cometgarden2"));
                msg.getOa().getBody().setContent(builder.toString());
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

    public static void getsendprogress(Long taskId, String accessToken) throws ApiException {
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

        public Long status;

        public String desc;

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
