package com.maxlong.camel.route.api;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午5:49:56
 * 类说明
 */
public class CamelEndpointRule implements Rule {

	private String rule;

	public void setRule(String rule){
		this.rule = rule;
	}

	@Override
	public String getRule() {
		return this.rule;
	}

}
 