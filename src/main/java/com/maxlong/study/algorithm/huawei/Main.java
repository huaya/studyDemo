package com.maxlong.study.algorithm.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();

        int[][]  arr = new int[a][3];
        for(int i = 0; i < a; i++){
            for(int j = 0; j < 3; j++){
                arr[i][j] = in.nextInt();
            }
        }

        int[] temp = arr[0];
        for(int i = 1; i < a; i++){
            temp = findMin(temp, arr[i]);
        }

        int min = temp[0];
        for(int i = 1; i < 3;i++){
            if(min > temp[i]){
                min = temp[i];
            }
        }
        System.out.println(min);
    }

    public static int[] findMin(int[] temp, int[] arr) {
        int[] na = new int[3];

        for(int i =0; i < 3; i++){
            int x,y;
            if(i == 0){
                x = 1; y = 2;
            } else if( i == 1){
                x = 2; y = 0;
            } else {
                x = 0; y = 1;
            }
            na[i] = Math.min(temp[x], temp[y]) + arr[i];
        }
        return na;
    }

}