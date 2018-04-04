package com.maxlong.camel;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointConfiguration;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����3:02:17 
* ��˵�� 
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