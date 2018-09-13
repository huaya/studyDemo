package com.maxlong.jdbc;

import com.maxlong.study.utils.DynamicPropertyHelper;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-9-12 18:00
 */
public class DBManger {

    private Connection connection;

    private PreparedStatement statement;

    private boolean autoCommit = true;

    private List<Object> parameters = new ArrayList<>();

    private DBManger() {
        init();
    }

    private static class DBMangerHolder {
        private static DBManger dbManger = new DBManger();
    }

    public static DBManger getInstance() {
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
            connection = DriverManager.getConnection(url, userName, password);
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

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeResource() {
        closeStatement();
        closeConnection();
        parameters.clear();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
        setParameter(statement);
        ResultSet resultSet = statement.executeQuery();
        if (!autoCommit) {
            connection.commit();
        }
        parameters.clear();
        return resultSet;

    }

    private void setParameter(PreparedStatement statement) throws SQLException {
        if (this.parameters != null && this.parameters.size() > 0) {

            for (int i = 0; i< parameters.size(); i++) {
                Object parameter = parameters.get(i);
                if(parameter instanceof Long){
                    statement.setLong(i + 1, (Long) parameter);
                } else if(parameter instanceof Long){
                    statement.setLong(i + 1, (Long) parameter);
                } else if(parameter instanceof String){
                    statement.setString(i + 1, (String) parameter);
                } else if(parameter instanceof BigDecimal){
                    statement.setBigDecimal(i + 1, (BigDecimal) parameter);
                } else if(parameter instanceof Boolean){
                    statement.setBoolean(i + 1, (Boolean) parameter);
                } else if(parameter instanceof Byte){
                    statement.setByte(i + 1, (Byte) parameter);
                } else if(parameter instanceof Short){
                    statement.setShort(i + 1, (Short) parameter);
                } else if(parameter instanceof Integer){
                    statement.setInt(i + 1, (Integer) parameter);
                } else if(parameter instanceof Double){
                    statement.setDouble(i + 1, (Double) parameter);
                } else if(parameter instanceof Byte[]){
                    statement.setBytes(i + 1, (byte[]) parameter);
                } else if(parameter instanceof Date){
                    statement.setDate(i + 1, (Date) parameter);
                } else if(parameter instanceof Time){
                    statement.setTime(i + 1, (Time) parameter);
                } else if(parameter instanceof Timestamp){
                    statement.setTimestamp(i + 1, (Timestamp) parameter);
                } else if(parameter instanceof java.util.Date){
                    statement.setTimestamp(i + 1, new Timestamp(((java.util.Date) parameter).getTime()) );
                } else {
                    throw new SQLDataException();
                }

            }
        }

    }

    public int executeUpdate(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
        setParameter(statement);
        int result = statement.executeUpdate();
        if (!autoCommit) {
            connection.commit();
        }
        parameters.clear();
        return result;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public DBManger addParameter(Object parameter) {
        this.parameters.add(parameter);
        return this;
    }
}
