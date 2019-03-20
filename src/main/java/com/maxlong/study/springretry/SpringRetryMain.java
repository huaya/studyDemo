package com.maxlong.study.springretry;

import java.util.Collections;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月29日 上午10:05:20
 * 类说明
 */
public class SpringRetryMain {
	public static void main(String[] args) throws Exception {
		RetryTemplate retryTemplate = new RetryTemplate();
		SimpleRetryPolicy policy = new SimpleRetryPolicy(3, Collections.<Class<? extends Throwable>, Boolean>singletonMap(Exception.class, true));
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(100);
		retryTemplate.setRetryPolicy(policy);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

		SpringRetryService service = new SpringRetryService();

		RecoveryCallback<Object> recoveryCallback = context -> {
			System.out.println("do recory operation");
			System.out.println(context.getAttribute("key1"));
			return "Callback";
		};

		String df = (String) retryTemplate.execute(service,recoveryCallback);
		System.out.println(df);
	}
}
 