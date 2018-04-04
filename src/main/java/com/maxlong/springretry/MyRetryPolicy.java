package com.maxlong.springretry;

import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月29日 上午10:14:51
 * 类说明
 */
public class MyRetryPolicy implements RetryPolicy{

	@Override
	public boolean canRetry(RetryContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(RetryContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public RetryContext open(RetryContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerThrowable(RetryContext arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

}
 