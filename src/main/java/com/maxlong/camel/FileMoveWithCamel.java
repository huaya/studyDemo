package com.maxlong.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��23�� ����2:59:42 
* ��˵�� 
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
 