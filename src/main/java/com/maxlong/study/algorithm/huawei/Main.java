package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int nums = in.nextInt();
            System.out.println(isPrime(nums));
        }
    }

    public static boolean isPrime(int num){
        if(num <= 2) return true;

        for(int i = 2; i <= num/2; i++){
            if(num % i ==0 ){
                return false;
            }
        }
        return true;
    }
}
