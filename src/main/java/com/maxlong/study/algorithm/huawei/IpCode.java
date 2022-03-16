package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

/**
 * 描述
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
 *
 * 所有的IP地址划分为 A,B,C,D,E五类
 *
 * A类地址从1.0.0.0到126.255.255.255;
 *
 * B类地址从128.0.0.0到191.255.255.255;
 *
 * C类地址从192.0.0.0到223.255.255.255;
 *
 * D类地址从224.0.0.0到239.255.255.255；
 *
 * E类地址从240.0.0.0到255.255.255.255
 *
 *
 * 私网IP范围是：
 *
 * 从10.0.0.0到10.255.255.255
 *
 * 从172.16.0.0到172.31.255.255
 *
 * 从192.168.0.0到192.168.255.255
 *
 *
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * （注意二进制下全是1或者全是0均为非法子网掩码）
 *
 * 注意：
 * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 *
 * 输入描述：
 * 多行字符串。每行一个IP地址和掩码，用~隔开。
 *
 * 请参考帖子https://www.nowcoder.com/discuss/276处理循环输入的问题。
 * 输出描述：
 * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
 *
 * 示例1
 * 输入：
 * 10.70.44.68~255.254.255.0
 * 1.0.0.1~255.0.0.0
 * 192.168.0.2~255.255.255.0
 * 19..0.~255.255.255.0
 * 复制
 * 输出：
 * 1 0 1 0 0 2 1
 * 复制
 * 说明：
 * 10.70.44.68~255.254.255.0的子网掩码非法，19..0.~255.255.255.0的IP地址非法，所以错误IP地址或错误掩码的计数为2；
 * 1.0.0.1~255.0.0.0是无误的A类地址；
 * 192.168.0.2~255.255.255.0是无误的C类地址且是私有IP；
 * 所以最终的结果为1 0 1 0 0 2 1
 * 示例2
 * 输入：
 * 0.201.56.50~255.255.111.255
 * 127.201.56.50~255.255.111.255
 * 复制
 * 输出：
 * 0 0 0 0 0 0 0
 * 复制
 * 说明：
 * 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
 */
public class IpCode {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a =0,b=0,c=0,d=0,e=0;
        int err = 0, pri=0;

        while (in.hasNextLine()) {
            String input = in.nextLine();
            if(input.equals("end")) break;

            String[] ipCode = input.split("~");
            String ip = ipCode[0];
            String code = ipCode[1];

            String[] ipS = ip.split("\\.");
            String[] codeS = code.split("\\.");

            if(ipS[0].equals("0") || ipS[0].equals("127")){
                continue;//跳过
            }

            if(isFeifaCode(codeS)){
                err++;
                continue;
            }

            switch (ipFenLei(ipS)) {
                case "err":
                    err++;
                    break;
                case "a":
                    a++;
                    if(isPriIp(ipS))  pri++;
                    break;
                case "b":
                    b++;
                    if(isPriIp(ipS))  pri++;
                    break;
                case "c":
                    c++;
                    if(isPriIp(ipS))  pri++;
                    break;
                case "d":
                    d++;
                    break;
                case "e":
                    e++;
                    break;
            }
        }
        System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + err + " " + pri);
    }

    public static boolean isFeifaCode(String[] codeS) {
        if(codeS.length != 4){
            return true;
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < codeS.length; i++){
            int val = Integer.valueOf(codeS[i]);
            if(val < 0 || val > 255){
                return true;
            }

            String binStr = Integer.toBinaryString(val);
            while (binStr.length() < 8) {
                binStr = "0" + binStr;
            }
            builder.append(binStr);
        }
        String allStr = builder.toString();
        if(!allStr.contains("0") || !allStr.contains("1")){
            return true;
        }
        return allStr.lastIndexOf("1") > allStr.indexOf("0");
    }

    public static String ipFenLei(String[] ipS ) {
        if(ipS.length != 4){
            return "err";
        }
        for(int i=0; i < ipS.length; i++) {
            int val = Integer.valueOf(ipS[i]);
            if(!(val >= 1 && val <= 255)){
                return "err";
            }
            if(i == 0){
                if (val >= 1 && val <= 126){
                    return "a";
                } else if(val >= 128 && val <= 191){
                    return "b";
                } else if(val >= 192 && val <= 223){
                    return "c";
                } else if(val >= 224 && val <= 239){
                    return "d";
                } else if(val >= 240 && val <= 255){
                    return "e";
                }
            }
        }
        return "";
    }

    public static boolean isPriIp(String[] ipS ) {
        int val1 = Integer.valueOf(ipS[0]);
        int val2 = Integer.valueOf(ipS[1]);

        if(val1 == 10 ||
                (val1 == 172 && val2 >= 16 && val2 <= 31) ||
                (val1 == 192 && val2 == 168)){
            return true;
        }
        return false;
    }
}
