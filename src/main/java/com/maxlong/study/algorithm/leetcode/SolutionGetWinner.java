package com.maxlong.study.algorithm.leetcode;

public class SolutionGetWinner {

    public static void main(String[] args) {
        int[] arr = {2,1,3,5,4,6,7};
        int k = 2;
        int result = new SolutionGetWinner().getWinner(arr, k);
        System.out.println(result);
    }

    public int getWinner(int[] arr, int k) {
        for(int x = 1, cnt = 0; cnt < k && x < arr.length; x++){
            int val0 = arr[0];
            if(val0 < arr[x]){
                arr[0] = arr[x];
                arr[x] = val0;
                cnt = 1;
            } else {
                cnt++;
            }
        }
        return arr[0];
    }
}
