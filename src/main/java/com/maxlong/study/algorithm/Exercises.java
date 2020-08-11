package com.maxlong.study.algorithm;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2020-08-11 22:55
 * 类说明:
 */
public class Exercises {

    public static void main(String[] args) {

    }

    public static void wordNumber() {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        char word = input.next().charAt(0);
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char u1 = Character.toUpperCase(word);
            char u2 = Character.toUpperCase(str.charAt(i));
            if ( u1 == u2) {
                num++;
            }
        }
        System.out.println(num);
    }
    public static void wordlength(){
        Scanner input = new Scanner(System.in);
        String s = "";
        while(input.hasNextLine()){
            s=input.nextLine();
            System.out.println(s.length()-1-s.lastIndexOf(" "));
        }
    }
}
