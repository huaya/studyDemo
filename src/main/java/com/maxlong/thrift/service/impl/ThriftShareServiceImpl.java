package com.maxlong.thrift.service.impl;

import org.apache.thrift.TException;

import com.maxlong.camel.route.RoutManager;
import com.maxlong.camel.route.api.CamelEndpointRule;
import com.maxlong.camel.service.CamelTestService;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;
import com.maxlong.thrift.demo.SharedService;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午3:31:55
 * 类说明
 */
public class ThriftShareServiceImpl implements SharedService.Iface{

	@Override
	public ResponseStruct SendReceive(RequestStruct request) throws TException {
		String reqId = request.getRequestData();
		CamelEndpointRule camelEndpointRule = new CamelEndpointRule();
		camelEndpointRule.setRule(reqId);
		CamelTestService camelTestService = RoutManager.route.route(camelEndpointRule, CamelTestService.class);
		ResponseStruct responseStruct = camelTestService.execute(request);
		return responseStruct;
	}

}
 