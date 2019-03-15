package com.maxlong.camel.route;

import lombok.extern.log4j.Log4j2;
import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import com.maxlong.spring.factory.Springfactory;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午4:46:04
 * 类说明
 */
@Log4j2
public class ServiceCamelRoute extends CamelRoute {

    @Override
    public RouteBuilder getRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                Map<String, String> routeMapping = getAnnotationRoute();
                for (String routeKey : routeMapping.keySet()) {
                    Object bean = getBean(routeMapping.get(routeKey));
                    if (bean == null || StringUtils.isBlank(routeKey)) {
                        log.error("route bean or route key is null,key:{},bean:{}", routeKey, routeMapping.get(routeKey));
                        continue;
                    }
                    from(routeKey).bean(bean).setErrorHandlerBuilder(new NoErrorHandlerBuilder());
                }
            }

            private Object getBean(String string) {
                return Springfactory.getInstance().getBean(string);
            }

        };
    }

    private Map<String, String> getAnnotationRoute() {
        Map<String, Object> annotationRouteMap = Springfactory.getInstance().getContext().getBeansWithAnnotation(RouteMapping.class);
        Map<String, String> routeMapping = new HashMap<>();
        for (String key : annotationRouteMap.keySet()) {
            RouteMapping routeMap = annotationRouteMap.get(key).getClass().getAnnotation(RouteMapping.class);
            String routeKey = routeMap.routeKey();
            String routeValue = routeMap.routeValue();
            if(StringUtils.isBlank(routeValue)){
                routeValue = key;
            }
            routeMapping.put(routeKey,routeValue);
            log.info("start route:{}-{}",routeKey,routeValue);
        }
        return routeMapping;
    }

}
 