package com.maxlong.camel.route.api;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����5:49:56 
* ��˵�� 
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
 