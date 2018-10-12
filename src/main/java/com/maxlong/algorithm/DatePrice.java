package com.maxlong.algorithm;

import com.maxlong.jdbc.DBManger;
import com.maxlong.study.utils.DateUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-9-12 16:50
 */
public class DatePrice {

    private static final Integer MaxPrice = 1000000;

    private static final Integer MaxAddTime = 30;

    private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    private static BigDecimal DIVIDE = new BigDecimal(100);

    public static void main(String[] args) {

        try {
            Date date = DateUtil.strToDate("2015-05-21 12:30:00", DATEFORMAT);
            DBManger dbManger = DBManger.getInstance();
            for (int i = 0; i < 3000000; i++) {
                String sql = "insert into t_date_price values(?,?)";
                dbManger.addParameter(date);
                dbManger.addParameter(new BigDecimal(new Random().nextInt(MaxPrice)).divide(DIVIDE));
                dbManger.executeUpdate(sql);
                date = DateUtil.addSecond(date, new Random().nextInt(MaxAddTime));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBManger.getInstance().closeResource();
        }


    }


}
