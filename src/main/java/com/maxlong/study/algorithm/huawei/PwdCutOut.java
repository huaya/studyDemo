package com.maxlong.study.algorithm.huawei;

/**
 * 描述
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
 * 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），
 * Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 *
 * 数据范围：字符串长度满足 1≤n≤2500
 * 输入描述：
 * 输入一个字符串（字符串的长度不超过2500）
 *
 * 输出描述：
 * 返回有效密码串的最大长度
 *
 * 示例1
 * 输入：
 *  ABBA
 * 输出：
 *  4
 * 示例2
 * 输入：
 *  ABBBA
 * 输出：
 *  5
 * 示例3
 * 输入：
 *  12HHHHA
 * 输出：
 *  4
 */
public class PwdCutOut {

    public static void main(String[] args) {
        String passwd = "AHHHA";
        int len = findMaxPwd(passwd, 1);
        System.out.println(len);
    }

    private static int findMaxPwd(String passwd, int max) {
        if(passwd.length() < max || passwd.length() == 1){
            return max;
        }
        for(int idx = 0; idx < passwd.length(); idx++){
            boolean equal = true;

            for(int i = 0, j = idx; i <= j; i++,j--){
                if(passwd.charAt(i)  != passwd.charAt(j)){
                    equal = false;
                    break;
                }
            }
            if(equal){
                max = Math.max(max, idx + 1);
            }
        }
        return findMaxPwd(passwd.substring(1), max);
    }
}
