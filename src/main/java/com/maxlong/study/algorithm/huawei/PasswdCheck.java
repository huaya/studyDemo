package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

/**
 *描述
 * 密码要求:
 *
 * 1.长度超过8位
 *
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 *
 * 3.不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
 *
 * 数据范围：输入的字符串长度满足 1≤n≤100
 * 输入描述：
 * 一组字符串。
 *
 * 输出描述：
 * 如果符合要求输出：OK，否则输出NG
 *
 * 示例1
 * 输入：
 *   021Abc9000
 *   021Abc9Abc1
 *   021ABC9000
 *   021$bc9000
 * 输出：
 *   OK
 *   NG
 *   NG
 *   OK
 */
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
