package com.maxlong.study.consistenthash;

import java.util.List;

/**
 * @author daofeng.xjf
 * @date 2019/2/16
 */
public interface LoadBalancer {

    Server select(List<Server> servers, Invocation invocation);

    Server select(Invocation invocation);

}
