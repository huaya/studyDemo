package com.maxlong.study.hystrix;

import com.netflix.hystrix.*;

public class HelloWorldFallBack {

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            String s = new CommandHelloworldWithFallBack(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadpoolwithfallback"))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                            .withCoreSize(10)
                            .withMaximumSize(10))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionTimeoutInMilliseconds(1000)
                            .withCircuitBreakerSleepWindowInMilliseconds(5000)
                            .withCircuitBreakerErrorThresholdPercentage(50)
                            .withCircuitBreakerRequestVolumeThreshold(1))
                    , "ccc", count % 20).execute();
            System.out.println(s);
            count++;
            Thread.currentThread().sleep(50);
        }

    }
}