package com.maxlong.algorithm;

import com.maxlong.jdbc.DBManger;
import com.maxlong.study.utils.DateUtil;

import java.util.Date;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-10-10 9:24
 */
public class SpotPageCfg {

    public static void main(String[] args) {

        try {
            DBManger dbManger = DBManger.getInstance();
            String sql = "INSERT INTO spot_page_cfg (sc_id, code, type, code_ch, pos, time, updated_at, created_at, created_by, deleted, is_config, is_up, name, name_en) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            dbManger.addParameter(1171);
            dbManger.addParameter("Home2");
            dbManger.addParameter("domestic-price");//domestic-price foreign-price
            dbManger.addParameter("首页/综合/内贸结算价");
            dbManger.addParameter("1");
            dbManger.addParameter("11:30");
            dbManger.addParameter(new Date());
            dbManger.addParameter(new Date());
            dbManger.addParameter("maxl");
            dbManger.addParameter("N");
            dbManger.addParameter("Y");
            dbManger.addParameter("Y");
            dbManger.addParameter("A00铝锭");
            dbManger.addParameter("Aluminum Ingot spot price:  East China");

            dbManger.executeUpdate(sql);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBManger.getInstance().closeResource();
        }

    }
}
