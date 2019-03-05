package com.maxlong.algorithm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maxlong.study.utils.FileUtil;
import java.util.ArrayList;
import java.util.List;


/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-1-13 17:17
 */
public class Temp {

    public static void main(String[] args) {
        String res = FileUtil.readFileToStr("D:\\workspace-mxl\\studyDemo\\src\\main\\resources\\json\\temp.json");
        JSONObject jsonObject = JSONObject.parseObject(res);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject platformAuthMap = data.getJSONObject("platformAuthMap");

        List<ResourceRule> resourceRuleList = new ArrayList<>();
        List<RecordRule> recordRuleList = new ArrayList<>();
        List<ApiRule> apiRuleList = new ArrayList<>();

        for (String key : platformAuthMap.keySet()) {
            JSONObject authObject = platformAuthMap.getJSONObject(key);

            JSONArray resourceTree = authObject.getJSONArray("resourceTree");
            for (Object object : resourceTree) {
                JSONObject tree = (JSONObject) object;
                addResourceRule(tree, resourceRuleList);
            }
            JSONArray record = authObject.getJSONArray("record");
            for (Object o : record) {
                JSONObject rule = (JSONObject) o;
                RecordRule recordRule = new RecordRule();
                recordRule.setRecordId(rule.getString("recordId"));
                recordRule.setAllow(rule.getString("allow"));
                JSONObject condition = rule.getJSONObject("conditions");
                RecordConditionDTO recordConditionDTO = new RecordConditionDTO();
                recordConditionDTO.setAllowDays(condition.getString("allowDays"));
                recordConditionDTO.setAllowBegin(condition.getString("allowBegin"));
                recordConditionDTO.setAllowEnd(condition.getString("allowEnd"));
                recordRule.setCondition(recordConditionDTO);
                recordRuleList.add(recordRule);
            }
            
            JSONArray api = authObject.getJSONArray("api");
            for (Object o : api) {
                JSONObject rule = (JSONObject) o;
                ApiRule apiRule = new ApiRule();
                apiRule.setApiId(rule.getString("apiId"));
                apiRule.setAllow(rule.getString("allow"));
                JSONObject condition = rule.getJSONObject("conditions");
                ApiConditionDTO apiConditionDTO = new ApiConditionDTO();
                apiConditionDTO.setAllowSize(condition.getString("allowSize"));
                apiRule.setCondition(apiConditionDTO);
                apiRuleList.add(apiRule);
            }
        }

        for (ResourceRule resourceRule : resourceRuleList) {
            System.out.println(JSONObject.toJSONString(resourceRule));
        }
        for (RecordRule recordRule : recordRuleList) {
            System.out.println(JSONObject.toJSONString(recordRule));
        }
        for (ApiRule apiRule : apiRuleList) {
            System.out.println(JSONObject.toJSONString(apiRule));
        }

        AddPermissionReq addPermissionReq = new AddPermissionReq();
        addPermissionReq.setName("测试");
        addPermissionReq.setRemark("测试");
        addPermissionReq.setStatus("N");
        addPermissionReq.setApiRules(apiRuleList);
        addPermissionReq.setResourceRules(resourceRuleList);
        addPermissionReq.setRecordRules(recordRuleList);
        System.out.println(JSONObject.toJSONString(addPermissionReq));
    }

    private static void addResourceRule(JSONObject tree, List<ResourceRule> resourceRuleList) {
        ResourceRule resourceRule = new ResourceRule();
        resourceRule.setAllow(tree.get("allow").toString());
        resourceRule.setResourceId(tree.getString("resourceId"));
        resourceRuleList.add(resourceRule);
        JSONArray childs = tree.getJSONArray("childs");
        for (Object child : childs) {
            JSONObject o = (JSONObject) child;
            addResourceRule(o, resourceRuleList);
        }
    }
}
