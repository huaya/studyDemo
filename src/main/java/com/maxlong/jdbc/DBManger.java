package com.maxlong.jdbc;

import com.maxlong.study.utils.DynamicPropertyHelper;
import org.springframework.util.Assert;
import java.sql.*;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-9-12 18:00
 */
public class DBManger {

    private Connection connection;

    private PreparedStatement statement;

    private boolean autoCommit = true;

    private DBManger() {
        init();
    }

    private static class DBMangerHolder {
        private static DBManger dbManger = new DBManger();
    }

    public static DBManger getInstance(){
        return DBMangerHolder.dbManger;
    }

    private void init() {
        String databasetype = DynamicPropertyHelper.getStringProperty("databasetype");

        String driver = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.driver");
        Assert.notNull(driver, "driver can not bean null");

        String url = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.url");
        Assert.notNull(url, "url can not bean null");

        String userName = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.username");
        Assert.notNull(userName, "userName can not bean null");

        String password = DynamicPropertyHelper.getStringProperty(databasetype + ".jdbc.password");
        Assert.notNull(password, "password can not bean null");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,userName,password);
            connection.setAutoCommit(autoCommit);
        } catch (ClassNotFoundException e) {
            closeConnection();
        } catch (SQLException e) {
            closeConnection();
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    private void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void closeStatement(){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeResource(){
        closeStatement();
        closeConnection();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        if(!autoCommit){
            connection.commit();
        }
        return resultSet;

    }

    public int executeUpdate(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
        int result =  statement.executeUpdate();
        if(!autoCommit){
            connection.commit();
        }
        return result;
    }

}
