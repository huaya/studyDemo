package com.maxlong.study.algorithm.huawei;

import java.util.*;

/**
 * 描述
 * 请实现一个计票统计系统。你会收到很多投票，其中有合法的也有不合法的，请统计每个候选人得票的数量以及不合法的票数。
 * （注：不合法的投票指的是投票的名字不存在n个候选人的名字中！！）
 *
 * 数据范围：每组输入中候选人数量满足 1 \le n \le 100 \1≤n≤100  ，总票数量满足 1 \le n \le 100 \1≤n≤100
 * 输入描述：
 * 第一行输入候选人的人数n，第二行输入n个候选人的名字（均为大写字母的字符串），第三行输入投票人的人数，第四行输入投票。
 *
 * 输出描述：
 * 按照输入的顺序，每行输出候选人的名字和得票数量（以" : "隔开，注：英文冒号左右两边都有一个空格！），最后一行输出不合法的票数，格式为"Invalid : "+不合法的票数。
 *
 * 示例1
 * 输入：
 * 4
 * A B C D
 * 8
 * A D E CF A GG A B
 * 输出：
 * A : 3
 * B : 1
 * C : 0
 * D : 1
 * Invalid : 3
 * 说明：
 * E CF GG三张票是无效的，所以Invalid的数量是3.
 */
public class Vote {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            int num = in.nextInt();

            in.nextLine();

            String[] names = in.nextLine().split(" ", num);
            Map<String, Integer> resMap = new LinkedHashMap<>();
            for(String name : names){
                resMap.put(name, 0);
            }

            int tpnum = in.nextInt();

            in.nextLine();

            String[] tps = in.nextLine().split(" ", tpnum);

            int invalidNum = 0;
            for(String tp : tps){
                if(resMap.containsKey(tp)){
                    resMap.put(tp, resMap.get(tp) + 1);
                } else {
                    invalidNum++;
                }

            }

            for(String name : resMap.keySet()){
                System.out.println(name + " : " + resMap.get(name));
            }
            System.out.println("Invalid : " + invalidNum);
        }
    }
}
