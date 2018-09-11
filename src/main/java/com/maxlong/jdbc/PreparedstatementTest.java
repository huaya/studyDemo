package com.maxlong.jdbc;

import com.maxlong.study.utils.DynamicPropertyHelper;
import org.springframework.util.Assert;
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
        String databasetype = DynamicPropertyHelper.getStringProperty("databasetype");

        String driver = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.driver");
        Assert.notNull(driver, "driver can not bean null");

        String url = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.url");
        Assert.notNull(url, "url can not bean null");

        String userName = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.username");
        Assert.notNull(userName, "userName can not bean null");

        String password = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.password");
        Assert.notNull(password, "password can not bean null");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,userName,password);

            statement = connection.prepareStatement("select * from spot_cfg");
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            DbObjectPrint dbObjectPrint = new DbObjectPrint(result);
            dbObjectPrint.print();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
