package com.maxlong.jdbc;

import lombok.extern.log4j.Log4j2;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/4 23:02
 * 类说明:
 */
@Log4j2
public class PreparedstatementTest {

    public static void main(String[] args) {

        try {
            DBManger dbManger = DBManger.getInstance();
            ResultSet result = dbManger.executeQuery("select * from proxy_log");
            DbObjectPrint dbObjectPrint = new DbObjectPrint(result);
            dbObjectPrint.print();
        } catch (SQLException e) {
            log.error("连接异常", e);
        } finally {
            DBManger.getInstance().closeResource();
        }

    }
}
