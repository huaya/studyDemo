package com.maxlong.jdbc;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: ma.xl E-mail: ma.xl@gjmetal.com
 * @version 创建时间：2019-3-15 13:41
 * 类说明:
 */
public class LogArea {

    private static final Logger logger = LoggerFactory.getLogger(LogArea.class);

    public static void main(String[] args) throws SQLException, IOException {

        String origin = "http://ip.cz88.net/data.php?ip=%s";
//        String sql = "select * from proxy_log where from_area is null";
        String sql = "select p.from,GROUP_CONCAT(p.id) as ids from proxy_log p group by p.from ";
        String update = "update proxy_log set from_area = ? where id in (%s)";

        DBManger dbManger = DBManger.getInstance();
        ResultSet resultSet = dbManger.executeQuery(sql);
        while (resultSet.next()){
            String from = (String) resultSet.getObject("from");
            String ids = (String) resultSet.getObject("ids");

            String originUrl = String.format(origin, from);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(originUrl);
            httpGet.setHeader("Content-type", "text/html;charset=gbk");

            HttpResponse httpResponse = httpClient.execute(httpGet);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if( statusCode != 200) continue;

            HttpEntity httpEntity =  httpResponse.getEntity();

            InputStream inputStream = httpEntity.getContent();
            BufferedInputStream bfi = new BufferedInputStream(inputStream);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            int read = bfi.read();
            while (read != -1){
                bao.write(read);
                read = bfi.read();
            }
            String content = new String(bao.toByteArray(), "GBK");
            logger.info("id:{}, from :{}, content :{}",ids, from, content);
            int leftBracketIdx = content.indexOf("(");
            int rightBracketIdx = content.indexOf(")");
            String iPAddr = content.substring(leftBracketIdx + 1, rightBracketIdx);
            String[] iPAddrColumn = iPAddr.split(",");
            String fromArea = iPAddrColumn[1].replace("'","");

            String updateF = String.format(update, ids);
            dbManger.addParameter(fromArea);
            dbManger.executeUpdate(updateF);
        }
    }
}
