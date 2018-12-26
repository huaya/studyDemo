package com.maxlong.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-12-24 16:34
 */
public class HttpClientDemo {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientDemo.class);

    public static void main(String[] args) {
        String origin = "https://demo.gjmetal.com/api/rest/querySpotValByProduct";

        String body = " {\n" +
                "   \"merchantId\":\"bloomberg\",\n" +
                "   \"pwd\":\"97170E4CAFB96A4311F79D9063E4E403\",\n" +
                "   \"reqDate\":\"20180615132500\",\n" +
                "   \"lang\":\"en\",\n" +
                "   \"version\":\"1.0.0\",\n" +
                "   \"p\":\"1\",\n" +
                "   \"size\":\"500\",\n" +
                "   \"startDate\":\"2018-12-07\",\n" +
                "   \"endDate\":\"2018-12-07\",\n" +
                "   \"productIdList\":[1264]\n" +
                "}";

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost(origin);
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");

            StringEntity entity = new StringEntity(body);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            httpPost.setEntity(entity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            logger.info("response code:{}", httpResponse.getStatusLine().getStatusCode());

            HttpEntity httpEntity =  httpResponse.getEntity();

            InputStream io = httpEntity.getContent();

            BufferedInputStream bis = new BufferedInputStream(io);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while(result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            String content = buf.toString();
            logger.info(content);
        } catch (Exception e){
            logger.error("error :{}", e);
        }



    }

}
