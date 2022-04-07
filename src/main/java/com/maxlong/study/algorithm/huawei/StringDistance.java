package com.maxlong.study.algorithm.huawei;

import java.util.*;

/**
 * 描述<br/>
 * Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。编辑距离的算法是首先由俄国科学家 Levenshtein 提出的，故又叫 Levenshtein Distance 。
 *
 * 例如：
 *
 * 字符串A: abcdefg
 *
 * 字符串B: abcdef
 *
 * 通过增加或是删掉字符 ”g” 的方式达到目的。这两种方案都需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
 *
 * 要求：
 *
 * 给定任意两个字符串，写出一个算法计算它们的编辑距离。
 *
 *
 * 数据范围：给定的字符串长度满足 1 \le len(str) \le 1000 \1≤len(str)≤1000
 *
 *
 * 输入描述：
 * 每组用例一共2行，为输入的两个字符串
 *
 * 输出描述：
 * 每组用例输出一行，代表字符串的距离
 *
 * 示例1
 * 输入：
 * abcdefg
 * abcdef
 * 输出：
 * 1
 */
public class StringDistance {

    /**
     *  动态规划
     *
     *  编辑距离是一类非常经典的动态规划的题目。 我们使用dp[i][j]表示字符串A的前i个字符与字符串B的前j个字符相同所需要的编辑距离。
     *  首先需要进行状态的初始化，当一个字符串为空时，编辑距离等于另一个字符串的长度 对于状态转移方程，需要分两种情况讨论：<br/>
     *  第一种情况，a[i]==b[j]，这种情况下，我们不需要进行编辑，dp[i][j]=dp[i-1][j-1]<br/>
     *
     *  第二种情况，a[i]!=b[j]，如果两个字符不相等，我们有三种处理方式：替换字符串b，编辑距离为dp[i-1][j-1]+1；插入一个字符与其相等，则编辑距离为dp[i-1][j]+1；
     *  删除该字符，编辑距离为dp[i][j-1]+1，三者取其小即可。 具体以下图为例
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str1 = in.nextLine();
            String str2 = in.nextLine();

            int[][] dp = new int[str1.length() + 1][str2.length() + 1];
            for(int i = 1; i <= str1.length(); i++){
                dp[i][0] = i;
            }
            for(int j = 1; j <= str2.length(); j++){
                dp[0][j] = j;
            }

            for(int i = 1; i <= str1.length(); i++){
                for (int j = 1; j <= str2.length(); j++) {
                    if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        int c = dp[i - 1][j - 1];
                        int u = dp[i][j - 1];
                        int d = dp[i - 1][j];
                        dp[i][j] = Math.min(c + 1, Math.min(u + 1, d + 1));
                    }
                }
            }
            System.out.println(dp[str1.length()][str2.length()]);
        }
    }

}
