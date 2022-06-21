package com.maxlong.study.algorithm.huawei;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.util.*;

public class YunSuan {

    static Set<String> numMap = new HashSet<String>(){
        {
            add("{");
            add("}");
            add("[");
            add("]");
            add("(");
            add(")");
            add("+");
            add("-");
            add("*");
            add("/");
        }
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String yunsuan = in.next();
            Stack<String> strings = new Stack<>();
            int numcnt = 0;
            for(int i = yunsuan.length() - 1; i >= 0; i--) {
                String c = yunsuan.substring(i, i + 1);
                if(numMap.contains(c)){
                    if(numcnt != 0){
                        strings.add(yunsuan.substring(i + 1 , i + numcnt + 1));
                    }
                    strings.add(c);
                    numcnt = 0;
                } else {
                    numcnt++;
                }
            }
                System.out.println(JSONObject.toJSONString(strings));
        }
    }

    private static String jisuan(String yunsuan) {
        if(yunsuan.startsWith("{") || yunsuan.startsWith("[") || yunsuan.startsWith("(")){
            Stack<String> strings = new Stack<>();
            for(int i = 0; i < yunsuan.length(); i++) {
                String c = yunsuan.substring(i, i + 1);
                strings.add(c);
                if(c.equals("}") || c.equals("]") || c.equals(")")){
                    StringBuilder builder = new StringBuilder();
                    strings.pop();
                    String x = strings.pop();
                    while (!x.equals("{") && !x.equals("[") && !x.equals("(")) {
                        builder.append(strings.pop());
                        x = strings.pop();
                    }
                    strings.add(jisuan(builder.toString()));
                }
            }
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < strings.size(); i++){
                if(i > 0 && i < strings.size() - 1){
                    result.append(strings.pop());
                }
            }
            return result.toString();
        }
        for(int i = 0; i < yunsuan.length(); i++) {
            String y = yunsuan.substring(i, i+1);
            if(y.equals("+") || y.equals("-")){
                String r = jisuan(yunsuan.substring(i + 1));
                Integer n1 = Integer.valueOf(yunsuan.substring(0, i));
                Integer n2 = Integer.valueOf(r);
                Integer z = y.equals("+") ? n1 + n2 : n1 - n2;
                return String.valueOf(z);
            } else if (y.equals("*") || y.equals("/")) {
                String next = yunsuan.substring(i + 1);
                String num = "";
                if(!next.equals("{") || next.equals("[") || next.equals("(")){
                    num = jisuan(yunsuan.substring(i + 1));
                } else {


                }


            }

        }
        return "";
    }
}
