package com.maxlong.springretry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月29日 上午9:58:52
 * 类说明
 */
public class SpringRetryService implements RetryCallback<Object, Exception>{

	@Override
	public String doWithRetry(RetryContext context) throws Exception {
		int retryCount = context.getRetryCount();
		System.out.println(retryCount);
		context.setAttribute("key1", "value1");
		if(retryCount != 0){
			System.out.println("realtime retry count: " + retryCount);
		}
		if(retryCount > 3){
			return service();
		}else{
			throw new Exception("exception");
		}
	}

	private String service() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "adsdas";
	}

}
 