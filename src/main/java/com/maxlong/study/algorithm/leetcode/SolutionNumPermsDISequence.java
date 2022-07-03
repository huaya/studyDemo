package com.maxlong.study.algorithm.leetcode;

import java.util.Arrays;

public class SolutionNumPermsDISequence {

    public static void main(String[] args) {
        String s = "DID";
        int result = new SolutionNumPermsDISequence().numPermsDISequence(s);
        System.out.println(result);

    }

    public int numPermsDISequence(String s) {
        int[] dp = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            dp[i] = 1;
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            for(int j = 0; j < s.length(); j++){
                dp[j] = jisuanPerms(c, dp, i + 1, j, s.length());
            }
        }
        return Arrays.stream(dp).sum();
    }

    private int jisuanPerms(char c, int[] dp, int x, int j, int length) {
        int num = c == 'D' ? x : length - x;
        return num * dp[j] + ((dp[j] - 1) * dp[j]) / 2;
    }

}
