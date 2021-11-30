package com.maxlong.study.algorithm;

/**
 * Created on 2020/9/16.
 *
 * @author xxxx
 * @Email xxxx
 */
public class MyMath {

    public static void main(String[] args) {
        System.out.println(getCube(3));
        System.out.println(Math.cbrt(3));
    }

    public static double sqrt(int num) {
        float v = 1;
        float p = 0.000001f;
        while (Math.abs(num/v - v) >= p*v) {
            v = (num/v + v)/2;
        }
        return v;
    }

    //迭代法求立方根
    public static double getCube(double input){
        double x = 1;
        double x1 = x - (x*x*x - input) / (3*x*x);
        while(x - x1 >0.000000001 || x - x1 < -0.000000001){		//判断精度
            x = x1;
            x1 = x - (x*x*x - input) / (3*x*x);
        }
        return x1;
    }

    //迭代法求平方根
    public static double getSqrt2(double input){
        double x = 1;
        double x1 = x - (x*x - input)/(2*x);
        while(x - x1 > 0.00000001 || x - x1 < -0.00000001){
            x = x1;
            x1 = x - (x*x - input)/(2*x);
        }
        return x1;
    }

}
