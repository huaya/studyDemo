package com.maxlong.camel.service.impl;

import org.springframework.stereotype.Service;

import com.maxlong.camel.service.CamelTestService;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;

/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年6月24日 上午10:56:25 
* 类说明 
*/
@Service(value = "com.maxlong.camel.CamelTestService")
public class CamelTestServiceImpl implements CamelTestService{

	@Override
	public ResponseStruct execute(RequestStruct request) {
		System.out.println("Hello world");
		ResponseStruct res = new ResponseStruct();
		res.setRequestId(request.getRequestId());
		res.setResponseData("hello world!");
		return res;
	}

}
 