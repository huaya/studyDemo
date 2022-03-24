package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

/**
 * 描述
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 *
 * 数据范围：输入的字符串长度满足 1 \le n \le 20 \1≤n≤20  ，保证输入的字符串中仅出现小写字母
 * 输入描述：
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 *
 * 输出描述：
 * 删除字符串中出现次数最少的字符后的字符串。
 *
 * 示例1
 * 输入：
 *  aabcddd
 * 输出：
 *  aaddd
 */
public class MinRepeate {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String str = in.nextLine();
            char[] chars = str.toCharArray();
            int min = 20;
            int[] nums = new int[26];

            for(int i = 0; i < chars.length; i++){
                char c = chars[i];
                int inx = c - 97;
                nums[inx] = nums[inx] + 1;
            }

            for(int i = 0; i < nums.length; i++){
                if(nums[i] > 0 && nums[i] < min){
                    min = nums[i];
                }
            }

            for(int i = 0; i < nums.length; i++){
                if(nums[i] == min){
                    str = str.replaceAll(String.valueOf((char)(i + 97)), "");
                }
            }
            System.out.println(str);
        }
    }
}
