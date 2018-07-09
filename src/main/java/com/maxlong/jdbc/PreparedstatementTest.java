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
        String driver = "oracle.jdbc.pool.OracleDataSource";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:MAXLONG";
        String userName = "study_m";
        String passWd = "123456";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,userName,passWd);

//            statement = connection.prepareStatement("insert into MXL_ORCL_STUDY (s_date,s_varchar,s_num) VALUES (?,?,?)");
            statement = connection.prepareStatement("select * from ACC_BAL_CFIT_RELATION");

//            statement.setDate(1,new Date(new java.util.Date().getTime()));
//            statement.setString(2,"20180705");
//            statement.setInt(3,1000);

            statement.executeQuery();
//            boolean result = statement.execute();

            ResultSet result = statement.getResultSet();
            ResultSetMetaData metaData = result.getMetaData();
            int countNum = metaData.getColumnCount();
            int row = 1;
            System.out.print("|     ROWNUM      ");
            for(int i =1 ;i <= countNum; i++){
                System.out.print("|         " + metaData.getColumnName(i) + "           ");
            }
            System.out.print("|");
            System.out.println();

            while (result.next()){
                System.out.print("|     " + row + "     ");
                for(int i =1 ;i <= countNum; i++){
                    String colName = metaData.getColumnName(i);
                    System.out.print("|           " + result.getObject(colName) + "           ");
                }
                row ++;
                System.out.print("|");
                System.out.println();
            }

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
