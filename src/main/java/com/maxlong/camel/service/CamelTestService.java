package com.maxlong.camel.service;

import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����3:37:52 
* ��˵�� 
*/

public interface CamelTestService {
	
	public ResponseStruct execute(RequestStruct request);
}
 