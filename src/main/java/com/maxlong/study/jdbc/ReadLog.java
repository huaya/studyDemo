package com.maxlong.study.jdbc;

import com.maxlong.study.utils.DateFormat;
import com.maxlong.study.utils.DateUtil;
import com.maxlong.study.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-8 13:58
 */
public class ReadLog {

    public static void main(String[] args) {
        String insert = "INSERT INTO `proxy_log`(`request_date`, `connecting`, `connect_port`, `from`, `from_port`) VALUES (?)";
        String maxDate = "2019-03-29 18:24:51";
        File file = new File("C:\\Users\\guojin\\Desktop\\nohup.out");
        List<String> logs = FileUtil.readLineFromFile(file, 0, "UTF-8");
        for (String log : logs) {
            if (!log.contains("connecting")) {
                continue;
            }
            log = log.replaceAll("nohup.out:", "");
            log = log.replaceAll(" INFO     connecting ", "|");
            log = log.replaceAll(" from ", "|");
            List<String> values = new ArrayList<>();
            String[] filds = log.split("\\|");
            String curDate = "";
            for (int i = 0; i < filds.length; i++) {
                String fild = filds[i];
                if (i == 0) {
                    values.add("'" + fild + "'");
                    curDate = fild;
                } else {
                    int p = fild.lastIndexOf(":");
                    values.add("'" + fild.substring(0,p) + "'");
                    values.add("'" + fild.substring(p + 1) + "'");
                }
            }
            if(DateUtil.compareDate(maxDate, curDate, DateFormat.STYLE1) >= 0){
                continue;
            }
            String value = StringUtils.join(values, ",");
            String sql = insert.replace("?", value);
            try {
                DBManger dbManger = DBManger.getInstance();
                dbManger.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(sql);
            }
        }
    }
}
