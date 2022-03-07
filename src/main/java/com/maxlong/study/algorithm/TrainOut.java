package com.maxlong.study.algorithm;

/**
 * Created on 2022/3/7.
 *
 * @author xxxx
 * @Email xxxx
 */

import java.util.*;

/**
 * 描述
 * 给定一个正整数N代表火车数量，0<N<10，接下来输入火车入站的序列，一共N辆火车，每辆火车以数字1-9编号，火车站只有一个方向进出，同时停靠在火车站的列车中，只有后进站的出站了，先进站的才能出站。
 * 要求输出所有火车出站的方案，以字典序排序输出。
 * 数据范围：1≤n≤10
 * 进阶：时间复杂度：O(n!)\O(n!) ，空间复杂度：O(n)\O(n)
 * 输入描述：
 * 有多组测试用例，每一组第一行输入一个正整数N（0
 *
 * 输出描述：
 * 输出以字典序从小到大排序的火车出站序列号，每个编号以空格隔开，每个输出序列换行，具体见sample。
 *
 * 示例：
 * 输入：
 *   3
 *  1 2 3
 * 输出：
 *  1 2 3
 *  1 3 2
 *  2 1 3
 *  2 3 1
 *  3 2 1
 */
public class TrainOut {

    static List<String> l = new ArrayList<>(); //储存结果

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            l.clear(); //静态变量，每次先清空
            int nums = in.nextInt();
            int[] id = new int[nums];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < nums; i++) {
                id[i] = in.nextInt();
            }
            trainOut(id, 0, stack, "", 0);
            //对结果集排序
            Collections.sort(l);
            for (String str : l) {
                System.out.println(str);
            }
        }
        in.close();
    }

    public static void trainOut(int[] id, int i, Stack<Integer> s, String str, int n) {
        if (n == id.length) {
            l.add(str); //如果所有火车均出栈则将当前结果保存
        }

        if (!s.empty()) { //栈非空时出栈
            int temp = s.pop();
            trainOut(id, i, s, str + temp + " ", n + 1);
            s.push(temp); //恢复现场
        }

        if (i < id.length) {
            s.push(id[i]);
            trainOut(id, i + 1, s, str, n);
            s.pop(); //恢复现场

        }
    }

}
