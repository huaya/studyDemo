package com.maxlong.study.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-1-9 22:55
 */
public class GjmetalData {

    public static void main(String[] args) throws SQLException {

        DBManger dbManger = DBManger.getInstance();
        ResultSet resultSet = dbManger.executeQuery("select id,product_id,publish_date,publish_point,low,high,middle from product_price");
        while (resultSet.next()) {
            Long id = (Long) resultSet.getObject("id");
            Long productId = (Long) resultSet.getObject("product_id");
            String low = (String) resultSet.getObject("low");
            String high = (String) resultSet.getObject("high");
            String middle = (String) resultSet.getObject("middle");
            BigDecimal tempLow = new BigDecimal(low);
            BigDecimal tempHigh = new BigDecimal(high);
            BigDecimal tempMiddle = new BigDecimal(middle);

            BigDecimal cm = tempLow.add(tempHigh).divide(new BigDecimal("2.00"), 2, BigDecimal.ROUND_HALF_UP);
            if(tempMiddle.compareTo(cm) != 0){
                System.out.println(id + "," + productId);
                String update = "update product_price set middle = '" + cm.toString() + "' where id = '" + id.toString() + "'";
//                dbManger.executeUpdate(update);
            }
        }
    }
}
