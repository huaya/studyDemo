package com.maxlong.jdbc;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/4 23:02
 * 类说明:
 */
public class PreparedstatementTest {

    public static void main(String[] args) {

        try {
            DBManger dbManger = DBManger.getInstance();
            ResultSet result = dbManger.executeQuery("select count(*) from product_price");
            DbObjectPrint dbObjectPrint = new DbObjectPrint(result);
            dbObjectPrint.print();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.getInstance().closeResource();
        }

    }
}
