package com.maxlong.camel.route;

import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import com.maxlong.spring.factory.Springfactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午4:46:04
 * 类说明
 */
public class ServiceCamelRoute extends CamelRoute {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCamelRoute.class);

    @Override
    public RouteBuilder getRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                Map<String, String> routeMapping = getAnnotationRoute();
                for (String routeKey : routeMapping.keySet()) {
                    Object bean = getBean(routeMapping.get(routeKey));
                    if (bean == null || StringUtils.isBlank(routeKey)) {
                        logger.error("route bean or route key is null,key:{},bean:{}", routeKey, routeMapping.get(routeKey));
                        continue;
                    }
                    from(routeKey).bean(bean).setErrorHandlerBuilder(new NoErrorHandlerBuilder());
                }
            }

            private Object getBean(String string) {
                return Springfactory.getBean(string);
            }

        };
    }

    private Map<String, String> getAnnotationRoute() {
        Map<String, Object> annotationRouteMap = Springfactory.context.getBeansWithAnnotation(RouteMapping.class);
        Map<String, String> routeMapping = new HashMap<>();
        for (String key : annotationRouteMap.keySet()) {
            RouteMapping routeMap = annotationRouteMap.get(key).getClass().getAnnotation(RouteMapping.class);
            String routeKey = routeMap.routeKey();
            String routeValue = routeMap.routeValue();
            if(StringUtils.isBlank(routeValue)){
                routeValue = key;
            }
            routeMapping.put(routeKey,routeValue);
            logger.info("start route:{}-{}",routeKey,routeValue);
        }
        return routeMapping;
    }

}
 