package com.maxlong.study.algorithm.leetcode;

public class SolutionIsPalindrome {

    public static void main(String[] args) {
        System.out.println(new SolutionIsPalindrome().isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i <= j;){
            char x = Character.toLowerCase(s.charAt(i));
            char y = Character.toLowerCase(s.charAt(j));

            if (!Character.isLetterOrDigit(x)) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(y)) {
                j--;
                continue;
            }
            if(x != y){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
