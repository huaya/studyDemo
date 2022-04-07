package com.maxlong.study.algorithm.huawei;

/**
 * 描述
 * 给定两个只包含小写字母的字符串，计算两个字符串的最大公共子串的长度。
 *
 * 注：子串的定义指一个字符串删掉其部分前缀和后缀（也可以不删）后形成的字符串。
 * 数据范围：字符串长度：1≤s≤150
 * 进阶：时间复杂度：O(n^3) ，空间复杂度：O(n)
 * 输入描述：
 * 输入两个只包含小写字母的字符串
 *
 * 输出描述：
 * 输出一个整数，代表最大公共子串的长度
 *
 * 示例1
 * 输入：
 *  asdfas
 *  werasdfaswer
 * 输出：
 *  6
 */
public class PublicStr {

    public static void main(String[] args) {
        String a = "a";
        String b = "ha";

        int len = maxPublicStr(a, b, 0);
        int len2 = maxPublicStrDp(a, b);
        System.out.println(len);
        System.out.println(len2);
    }


    private static int maxPublicStrDp(String a, String b) {
        int max = 0;
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for(int i = 0; i < a.length(); i++){
            for(int j = 0; j < b.length(); j++){
                if(a.charAt(i) == b.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }
        return max;
    }

    private static int maxPublicStr(String a, String b, int max) {
        for(int idx = 1; idx <= a.length(); idx++){
            if(idx > b.length() || !b.contains(a.substring(0, idx))){
                break;
            }
            max = Math.max(max, idx);
        }

        if(a.length() - 1 < max || a.length() == 1){
            return max;
        }
        return maxPublicStr(a.substring(1), b, max);
    }
}
