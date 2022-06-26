package com.maxlong.study.algorithm.leetcode;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *  给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SolutionLongestSubstring {

    public static void main(String[] args) throws IOException {
        InputStream inputIs = SolutionLongestSubstring.class.getClassLoader().getResourceAsStream("json/substring");
        String input = CharStreams.toString(new InputStreamReader(inputIs, StandardCharsets.UTF_8));
        System.out.println(new SolutionLongestSubstring().lengthOfLongestSubstring2(input));
    }

    public int lengthOfLongestSubstringBest(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        int length = s.length(), maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < length; end++) {
            Character ch = s.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(map.get(ch), start);
            }
            maxLength = Math.max(maxLength, end - start + 1);
            map.put(ch, end + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring(String s) {
        String tmp = "";
        int maxLength = 0;
        int temLen = 0;
        for (int i = 0; i < s.length() && maxLength <= s.length() + tmp.length() - i; i++) {
            String ch = s.charAt(i) + "";
            int idx = tmp.indexOf(ch);
            if (idx == -1) {
                temLen++;
                tmp = tmp + ch;
            } else {
                maxLength = Math.max(maxLength, temLen);
                i = i + idx - tmp.length();
                tmp = "";
                temLen = 0;
            }
        }
        return Math.max(maxLength, temLen);
    }
}
