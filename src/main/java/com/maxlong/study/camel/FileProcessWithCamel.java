package com.maxlong.study.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 下午3:02:17
 * 类说明
 */
public class FileProcessWithCamel {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("timer://timer1?period=1000").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("add" + exchange);
                    }
                });
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