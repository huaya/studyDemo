package com.maxlong.camel.service.impl;

import org.springframework.stereotype.Service;

import com.maxlong.camel.service.CamelTestService;
import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��24�� ����10:56:25 
* ��˵�� 
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
 