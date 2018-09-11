package com.maxlong.thrift.service.impl;

import com.maxlong.camel.route.RoutManager;
import com.maxlong.camel.route.api.CamelEndpointRule;
import com.maxlong.service.BaseCamelService;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;
import com.maxlong.thrift.demo.SharedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午3:31:55
 * 类说明
 */
public class ThriftShareServiceImpl implements SharedService.Iface {

    private static final Logger logger = LoggerFactory.getLogger(ThriftShareServiceImpl.class);

    @Override
    public ResponseStruct SendReceive(RequestStruct request) {
        ResponseStruct responseStruct;
        String reqId = request.getRequestId();
        CamelEndpointRule camelEndpointRule = new CamelEndpointRule();
        camelEndpointRule.setRule(reqId);
        BaseCamelService baseCamelService = RoutManager.getInstance().getRoute().route(camelEndpointRule, BaseCamelService.class);
        if (baseCamelService == null) {
            logger.error("get camelService fail,routekey:{}", reqId);
            responseStruct = new ResponseStruct();
            responseStruct.setRequestId(reqId);
            responseStruct.setResponseData("route fail!");
            return responseStruct;
        }
        responseStruct = baseCamelService.execute(request);
        return responseStruct;
    }

}
 