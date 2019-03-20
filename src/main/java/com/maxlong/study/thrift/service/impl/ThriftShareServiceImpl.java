package com.maxlong.study.thrift.service.impl;

import com.maxlong.study.camel.route.RoutManager;
import com.maxlong.study.camel.route.api.CamelEndpointRule;
import com.maxlong.study.service.BaseCamelService;
import com.maxlong.study.thrift.demo.RequestStruct;
import com.maxlong.study.thrift.demo.ResponseStruct;
import com.maxlong.study.thrift.demo.SharedService;
import lombok.extern.log4j.Log4j2;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午3:31:55
 * 类说明
 */
@Log4j2
public class ThriftShareServiceImpl implements SharedService.Iface {

    @Override
    public ResponseStruct SendReceive(RequestStruct request) {
        ResponseStruct responseStruct;
        String reqId = request.getRequestId();
        CamelEndpointRule camelEndpointRule = new CamelEndpointRule();
        camelEndpointRule.setRule(reqId);
        BaseCamelService baseCamelService = RoutManager.getInstance().getRoute().route(camelEndpointRule, BaseCamelService.class);
        if (baseCamelService == null) {
            log.error("get camelService fail,routekey:{}", reqId);
            responseStruct = new ResponseStruct();
            responseStruct.setRequestId(reqId);
            responseStruct.setResponseData("route fail!");
            return responseStruct;
        }
        responseStruct = baseCamelService.execute(request);
        return responseStruct;
    }

}
 