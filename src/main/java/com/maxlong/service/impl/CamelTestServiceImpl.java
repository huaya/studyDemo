package com.maxlong.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxlong.camel.route.RouteMapping;
import com.maxlong.service.BaseCamelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 上午10:56:25
 * 类说明
 */
@Log4j2
@RouteMapping(routeKey = "direct://maxlong_camelTestService")
@Service(value = "com.maxlong.camel.CamelTestServiceImpl")
public class CamelTestServiceImpl implements BaseCamelService {

	@Override
	public ResponseStruct execute(RequestStruct request) {
		log.info("received request : {}", JSONObject.toJSONString(request));
		ResponseStruct res = new ResponseStruct();
		res.setRequestId(request.getRequestId());
		res.setResponseData("hello world! --by CamelTestServiveImpl");
		return res;
	}

}
 