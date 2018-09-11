package com.maxlong.jdbc;

import com.maxlong.study.utils.StringUtil;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-9-10 15:59
 */
public class DbObjectPrint {

    private List<Map<String, String>> dbObjects;

    private Map<String, Integer> columLength;

    public DbObjectPrint(ResultSet resultSet) throws SQLException {
        columLength = new LinkedHashMap<>();
        dbObjects = new ArrayList<>();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int countNum = metaData.getColumnCount();
        Map<String, String> headObject = new LinkedHashMap<>();
        for (int i = 1; i <= countNum; i++) {
            String columnName = metaData.getColumnName(i);
            columLength.put(columnName, StringUtil.getLengthWithChiness(columnName));
            headObject.put(columnName, columnName);
        }
        dbObjects.add(headObject);

        while (resultSet.next()) {
            Map<String, String> dbObject = new LinkedHashMap<>();
            for (int i = 1; i <= countNum; i++) {
                String colName = metaData.getColumnName(i);
                Integer maxlength = columLength.get(colName);
                Object object = resultSet.getObject(colName);
                String columValue = object == null?"NULL":object.toString();
                int columValueLength = StringUtil.getLengthWithChiness(columValue);
                columLength.put(colName, columValueLength > maxlength ? columValueLength : maxlength);
                dbObject.put(colName, columValue);
            }
            dbObjects.add(dbObject);
        }

    }

    public void print() {

        for (Map<String, String> column : dbObjects) {
            for (String columnName : column.keySet()) {
                String printStr = StringUtil.fillAroundStrWithBlank(column.get(columnName),columLength.get(columnName));
                System.out.print("|  " + printStr + "  ");
            }
            System.out.print("|");
            System.out.println();
        }

    }
}
