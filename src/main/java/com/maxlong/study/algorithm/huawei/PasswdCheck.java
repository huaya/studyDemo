package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

public class PasswdCheck {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String passwd = in.nextLine();
            if(passwd.length() < 8){
                System.out.println("NG");
                continue;
            }
            int l = 0, u = 0, n = 0, o = 0;
            char[] chars = passwd.toCharArray();
            for(int i = 0; i < passwd.length(); i++){
                char c = chars[i];
                if('a' <= c && c <= 'z'){
                    l = 1;
                } else if('A' <= c && c <= 'Z'){
                    u = 1;
                } else if('0' <= c && c <= '9'){
                    n = 1;
                } else if(c != ' ' && c != '\n') {
                    o = 1;
                }
            }
            if((l + u + n + o) < 3 ){
                System.out.println("NG");
                continue;
            }

            boolean hefa = true;
            for(int i = 0; i < passwd.length() - 3; i++){
                String cstr = passwd.substring(i, i + 3);

                String cstr2 = passwd.substring(i, i + 3);
                String cstr3 = passwd.substring(i + 3);
                if(cstr3.contains(cstr2)){
                    hefa = false;
                    break;
                }
            }
            if(!hefa){
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
    }

}
