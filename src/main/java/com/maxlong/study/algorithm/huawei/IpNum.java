package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

public class IpNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String ip = in.nextLine();
            String num = in.nextLine();

            String binNum = "";
            for(String ipN : ip.split("\\.")){
                String binStr = Long.toBinaryString(Long.valueOf(ipN));
                while (binStr.length() < 8) {
                    binStr = "0" + binStr;
                }
                binNum = binNum + binStr;
            }
            long allIpn = Long.valueOf(binNum, 2);
            System.out.println(allIpn);

            String bin = Long.toBinaryString(Long.valueOf(num));
            while (bin.length() < 32) {
                bin = "0" + bin;
            }

            String binToIp = "";
            for(int i = 0; i < 32; i+=8){
                String tmp = bin.substring(i, i + 8);
                binToIp = binToIp + "." + Long.valueOf(tmp, 2);
            }
            System.out.println(binToIp.substring(1));
        }
    }
}
