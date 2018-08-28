package com.maxlong.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxlong.camel.route.RouteMapping;
import com.maxlong.service.BaseCamelService;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 描述：
 * 作者： ma.xl
 * 版本： 1.0.0
 * 时间： 2018-8-28 16:19
 */
@RouteMapping(routeKey = "direct://maxlong_camelTest2Service")
@Service(value = "com.maxlong.camel.CamelTest2ServiveImpl")
public class CamelTest2ServiveImpl implements BaseCamelService {

    private static final Logger logger = LoggerFactory.getLogger(CamelTest2ServiveImpl.class);

    @Override
    public ResponseStruct execute(RequestStruct request) {
        logger.info("received request : {}", JSONObject.toJSONString(request));
        ResponseStruct res = new ResponseStruct();
        res.setRequestId(request.getRequestId());
        res.setResponseData("hello world! --by CamelTest2ServiveImpl");
        return res;
    }
}
