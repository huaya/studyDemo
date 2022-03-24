package com.maxlong.study.algorithm.huawei;

import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String a = in.nextLine();
            String b = in.nextLine();
            int v = in.nextInt();

            int min = 0;
            int m = 0;
            for(int i=0; i< a.length(); i++){
                int x = a.charAt(i) - b.charAt(i);
                if(x < v){
                    m++;
                } else {
                    min = Math.max(m, min);
                    m = 0;
                }
            }
            System.out.println(min);


        }
    }
}
