package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine().replaceAll(",", "");

            String[] cars = line.split("0");

            int minNum = 0;
            for(String car : cars){
                minNum = minNum + findMin(line);
            }
            System.out.println(minNum);

            char c = '\n';
        }
    }

    public static int findMin(String line) {
        int len = line.length();
        if( len == 0){
            return 0;
        }
        int n = len/3;
        int m = len%3;
        return n + (m > 0 ? 1 : 0);
    }
}