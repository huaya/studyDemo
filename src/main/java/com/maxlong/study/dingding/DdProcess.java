package com.maxlong.study.dingding;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiProcessSaveRequest;
import com.dingtalk.api.request.OapiProcessWorkrecordCreateRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiProcessSaveResponse;
import com.dingtalk.api.response.OapiProcessWorkrecordCreateResponse;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maxlong.study.dingding.DingdingUser.*;
import static com.dingtalk.api.request.OapiProcessWorkrecordCreateRequest.*;
/**
 * Created on 2019/12/11.
 *
 * @author MaXiaolong
 */
public class DdProcess {


    public static void main(String[] args) throws ApiException {
//        System.out.println(saveProcess());
        System.out.println(createWorkrecord("PROC-A817E8CE-64F8-4DB5-9B03-ED988EB5AD47", "275406665838761686"));
    }

    private static String saveProcess() throws ApiException {

        String accessToken = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/process/save");

        OapiProcessSaveRequest request = new OapiProcessSaveRequest();
        OapiProcessSaveRequest.SaveProcessRequest saveProcessRequest = new OapiProcessSaveRequest.SaveProcessRequest();
        saveProcessRequest.setDisableFormEdit(true);
        saveProcessRequest.setName("test3.0");
//        saveProcessRequest.setProcessCode("PROC-BE7FC6B2-E95B-45CA-AD9A-A62819EDA2FE");
        saveProcessRequest.setFakeMode(true);

        // 注意，每种表单组件，对应的componentName是固定的，参照一下示例代码
        List<OapiProcessSaveRequest.FormComponentVo> formComponentList = Lists.newArrayList();

        // 单行文本框
        OapiProcessSaveRequest.FormComponentVo singleInput = new OapiProcessSaveRequest.FormComponentVo();
        singleInput.setComponentName("TextField");
        OapiProcessSaveRequest.FormComponentPropVo singleInputProp = new OapiProcessSaveRequest.FormComponentPropVo();
        singleInputProp.setRequired(true);
        singleInputProp.setLabel("单行输入框");
        singleInputProp.setPlaceholder("请输入");
        singleInputProp.setId("TextField-J78F056R");
        singleInput.setProps(singleInputProp);
        formComponentList.add(singleInput);

        // 多行文本框
//        OapiProcessSaveRequest.FormComponentVo multipleInput = new OapiProcessSaveRequest.FormComponentVo();
//        multipleInput.setComponentName("TextareaField");
//        OapiProcessSaveRequest.FormComponentPropVo multipleInputProp = new OapiProcessSaveRequest.FormComponentPropVo();
//        multipleInputProp.setRequired(true);
//        multipleInputProp.setLabel("多行输入框");
//        multipleInputProp.setPlaceholder("请输入");
//        multipleInputProp.setId("TextareaField-J78F056S");
//        multipleInput.setProps(multipleInputProp);
//        formComponentList.add(multipleInput);

        // 金额组件
//        OapiProcessSaveRequest.FormComponentVo moneyComponent = new OapiProcessSaveRequest.FormComponentVo();
//        moneyComponent.setComponentName("MoneyField");
//        OapiProcessSaveRequest.FormComponentPropVo moneyComponentProp = new OapiProcessSaveRequest.FormComponentPropVo();
//        moneyComponentProp.setRequired(true);
//        moneyComponentProp.setLabel("金额（元）大写");
//        moneyComponentProp.setPlaceholder("请输入");
//        moneyComponentProp.setId("MoneyField-J78F0571");
//        moneyComponentProp.setNotUpper("1"); // 是否禁用大写
//        moneyComponent.setProps(moneyComponentProp);
//        formComponentList.add(moneyComponent);

        // 数字输入框
//        OapiProcessSaveRequest.FormComponentVo numberComponent = new OapiProcessSaveRequest.FormComponentVo();
//        numberComponent.setComponentName("NumberField");
//        OapiProcessSaveRequest.FormComponentPropVo numberComponentProp = new OapiProcessSaveRequest.FormComponentPropVo();
//        numberComponentProp.setRequired(true);
//        numberComponentProp.setLabel("数字输入框带单位");
//        numberComponentProp.setPlaceholder("请输入");
//        numberComponentProp.setId("NumberField-J78F057N");
//        numberComponentProp.setUnit("元");
//        numberComponent.setProps(numberComponentProp);
//        formComponentList.add(numberComponent);

        // 日期
//        OapiProcessSaveRequest.FormComponentVo dateComponent = new OapiProcessSaveRequest.FormComponentVo();
//        dateComponent.setComponentName("DDDateField");
//        OapiProcessSaveRequest.FormComponentPropVo dateComponentProp = new OapiProcessSaveRequest.FormComponentPropVo();
//        dateComponentProp.setRequired(true);
//        dateComponentProp.setLabel("日期");
//        dateComponentProp.setPlaceholder("请选择");
//        dateComponentProp.setUnit("小时"); // 小时或天
//        dateComponentProp.setId("DDDateField-J8MTJZVE");
//        dateComponent.setProps(dateComponentProp);
//        formComponentList.add(dateComponent);

        // 日期区间
//        OapiProcessSaveRequest.FormComponentVo dateRangeComponent = new OapiProcessSaveRequest.FormComponentVo();
//        dateRangeComponent.setComponentName("DDDateRangeField");
//        OapiProcessSaveRequest.FormComponentPropVo dateRangeComponentProp = new OapiProcessSaveRequest.FormComponentPropVo();
//        dateRangeComponentProp.setRequired(true);
//        dateRangeComponentProp.setLabel(JSON.toJSONString(Arrays.asList("a", "b")));
//        dateRangeComponentProp.setPlaceholder("请选择");
//        dateRangeComponentProp.setUnit("小时"); // 小时或天
//        dateRangeComponentProp.setId("DDDateRangeField-J78F057Q");
//        dateRangeComponent.setProps(dateRangeComponentProp);
//        formComponentList.add(dateRangeComponent);

        saveProcessRequest.setFormComponentList(formComponentList);
        request.setSaveProcessRequest(saveProcessRequest);

        OapiProcessSaveResponse response = client.execute(request, accessToken);
        System.out.println(JSON.toJSONString(response));
        return response.getResult().getProcessCode();
    }

    private static String createWorkrecord(String processCode, String userId) throws ApiException {
        String accessToken = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/process/workrecord/create");
        OapiProcessWorkrecordCreateRequest req = new OapiProcessWorkrecordCreateRequest();
        SaveFakeProcessInstanceRequest obj1 = new SaveFakeProcessInstanceRequest();
        obj1.setProcessCode(processCode);
        obj1.setOriginatorUserId(userId);
        List<FormComponentValueVo> list3 = new ArrayList<>();
        FormComponentValueVo obj4 = new FormComponentValueVo();

        obj4.setName("单行输入框");
        obj4.setValue("value");
        obj4.setExtValue("ext");
        list3.add(obj4);

        obj1.setFormComponentValues(list3);
        obj1.setUrl("http://www.dingtalk.com");
        req.setRequest(obj1);

        OapiProcessWorkrecordCreateResponse rsp = client.execute(req, accessToken);
        return rsp.getResult().getProcessInstanceId();
    }


}
