package com.maxlong.study.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxlong.study.camel.route.RouteMapping;
import com.maxlong.study.service.BaseCamelService;
import com.maxlong.study.thrift.demo.RequestStruct;
import com.maxlong.study.thrift.demo.ResponseStruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * 描述：
 * 作者： ma.xl
 * 版本： 1.0.0
 * 时间： 2018-8-28 16:19
 */
@Log4j2
@RouteMapping(routeKey = "direct://maxlong_camelTest2Service")
@Service(value = "com.maxlong.study.camel.CamelTest2ServiveImpl")
public class CamelTest2ServiveImpl implements BaseCamelService {

    @Override
    public ResponseStruct execute(RequestStruct request) {
        log.info("received request : {}", JSONObject.toJSONString(request));
        ResponseStruct res = new ResponseStruct();
        res.setRequestId(request.getRequestId());
        res.setResponseData("hello world! --by CamelTest2ServiveImpl");
        return res;
    }
}
