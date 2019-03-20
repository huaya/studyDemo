package com.maxlong.study.service;

import com.maxlong.study.thrift.demo.RequestStruct;
import com.maxlong.study.thrift.demo.ResponseStruct;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午3:37:52
 * 类说明
 */

public interface BaseCamelService {

	ResponseStruct execute(RequestStruct request);

}
 