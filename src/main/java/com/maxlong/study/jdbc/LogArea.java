package com.maxlong.study.jdbc;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
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
@Log4j2
public class LogArea {

    public static void main(String[] args) throws SQLException, IOException {

        String origin = "http://ip.cz88.net/data.php?ip=%s";
        String sql = "select p.from,GROUP_CONCAT(p.id ORDER BY p.id) as ids from proxy_log p where from_area is null group by p.from ";
        String update = "update proxy_log set from_area = ? where `from` = ? and from_area is null";

        DBManger dbManger = DBManger.getInstance();
        ResultSet resultSet = dbManger.executeQuery(sql);
        while (resultSet.next()){
            String from = (String) resultSet.getObject("from");

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
            log.info("from :{}, content :{}",from, content);
            int leftBracketIdx = content.indexOf("(");
            int rightBracketIdx = content.indexOf(")");
            String iPAddr = content.substring(leftBracketIdx + 1, rightBracketIdx);
            String[] iPAddrColumn = iPAddr.split(",");
            String fromArea = iPAddrColumn[1].replace("'","");
            dbManger.addParameter(fromArea);
            dbManger.addParameter(from);
            dbManger.executeUpdate(update);
        }
    }
}
