package com.maxlong.camel.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxlong.camel.route.RouteMapping;
import com.maxlong.camel.service.BaseCamelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 上午10:56:25
 * 类说明
 */
@RouteMapping(routeKey = "direct://maxlong_camelTestService")
@Service(value = "com.maxlong.camel.CamelTestServiceImpl")
public class CamelTestServiceImpl implements BaseCamelService {

	private static final Logger logger = LoggerFactory.getLogger(CamelTestServiceImpl.class);

	@Override
	public ResponseStruct execute(RequestStruct request) {
		logger.info("received request : {}", JSONObject.toJSONString(request));
		ResponseStruct res = new ResponseStruct();
		res.setRequestId(request.getRequestId());
		res.setResponseData("hello world! --by CamelTestServiveImpl");
		return res;
	}

}
 