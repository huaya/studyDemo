package com.maxlong.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午2:59:42
 * 类说明
 */
public class FileMoveWithCamel {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("jjj30000").bean("sdsdsd");
            }
        });
        context.start();
        boolean loop =true;
        while(loop){
            Thread.sleep(25000);
        }
        context.stop();
    }
}  
 