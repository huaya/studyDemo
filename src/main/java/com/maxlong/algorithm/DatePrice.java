package com.maxlong.algorithm;

import com.maxlong.study.utils.DateUtil;

import java.math.BigDecimal;
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

        Date date = DateUtil.strToDate("2015-05-21 12:30:00", DATEFORMAT);

        for (int i = 0; i < 3000000; i++) {
            StringBuffer sqlBuffer = new StringBuffer("insert into t_date_price values(?,?)");

            System.out.print("Time: " + DateUtil.dateToStr(date,DATEFORMAT) + ", ");
            System.out.println("Price: " + new BigDecimal(new Random().nextInt(MaxPrice)).divide(DIVIDE));
            date = DateUtil.addSecond(date,new Random().nextInt(MaxAddTime));
        }

    }


}
