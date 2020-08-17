package com.maxlong.study.algorithm;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2020-08-11 22:55
 * 类说明:
 */
public class Exercises {

    public static void main(String[] args) throws IOException {
        knapsack();
    }

    /**
     * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
     * 主件	附件
     * 电脑	打印机，扫描仪
     * 书柜	图书
     * 书桌	台灯，文具
     * 工作椅	无
     * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
     *     设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
     * v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
     *     请你帮助王强设计一个满足要求的购物单。
     *
     * 输入描述:
     *  输入的第 1 行，为两个正整数，用一个空格隔开：N m
     * （其中 N （ <32000 ）表示总钱数， m （ <60 ）为希望购买物品的个数。）
     *  从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
     * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
     *
     * 输出描述:
     *  输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。
     *
     **/
    public static void knapsack() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            String[] line1 = str.split(" ");
            Integer money = Integer.valueOf(line1[0]);
            Integer num = Integer.valueOf(line1[1]);
            Map<Integer, Good> goodMap = new HashMap<>();

            for (int i = 0; i < num; i++) {
                String[] line = b.readLine().split(" ");
                Integer price = Integer.valueOf(line[0]);
                Integer weight = Integer.valueOf(line[1]);
                Integer index = Integer.valueOf(line[2]);
                Good good;
                if(index == 0){
                    good = new Good(i + 1, price, weight);
                } else {
                    good = goodMap.get(index);
                    if(good != null){
                        good.addAnnex(price, weight);
                    }
                }
                if(good != null){
                    if(good.getTotalPrice() <= money){
                        goodMap.put(good.index, good);
                    } else {
                        goodMap.remove(good.index);
                    }
                }
            }
            List<Good> goods = new ArrayList<>(goodMap.values());
            goods.sort((g1, g2) -> {
                if(g2.getWeightPriceRate() - g1.getWeightPriceRate() > 0){
                    return 1;
                } else {
                    return -1;
                }
            });
            Integer currPrice = 0;
            Integer costWeight = 0;
            for (Good good : goods) {
                if(currPrice + good.getTotalPrice() > money) break;
                costWeight += good.getAllCostWeight();
                currPrice += good.getTotalPrice();
            }
            System.out.println(costWeight);
        }
    }

    public static class Good {

        private Integer index;

        private Integer price;

        private Integer weight;

        private List<Good> annex = new ArrayList<>();

        public Good(Integer index, Integer price, Integer weight) {
            this.index = index;
            this.price = price;
            this.weight = weight;
        }

        public void addAnnex(Integer price, Integer weight){
            this.annex.add(new Good(this.index, price, weight));
        }

        public double getWeightPriceRate(){
            return getAllCostWeight()/getTotalPrice();
        }

        public Integer getTotalPrice(){
            Integer price = this.price;
            for (Good good : annex) {
                price += good.price;
            }
            return price;
        }

        public Integer getAllCostWeight(){
            Integer costWeight = this.price * weight;
            for (Good good : annex) {
                costWeight += good.getSelfCostWeight();
            }
            return costWeight;
        }

        public Integer getSelfCostWeight(){
            return this.price * weight;
        }

    }


    /**
     * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
     */
    public static void binOneNum() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            int num = Integer.valueOf(str);
            int cnt = 0;
            while (num >= 2) {
                int remainder = num%2;
                if(remainder == 1){
                    cnt++;
                }
                num = num/2;
            }
            if(num == 1) cnt++;
            System.out.println(cnt);
        }
    }

    /**
     * 给定n个字符串，请对n个字符串按照字典序排列。
     * <p>
     * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
     * <p>
     * 数据输出n行，输出结果为按照字典序排列的字符串。
     */
    public static void sortStrs() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            Integer num = Integer.valueOf(str);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                list.add(b.readLine());
            }
            list.sort(Comparator.naturalOrder());
            for (String s : list) {
                System.out.println(s);
            }
        }
    }


    /**
     * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
     * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
     */
    public static void overturnSentence() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            String newStr = "";
            String[] strs = str.split(" ");
            for (int i = strs.length - 1; i >= 0; i--) {
                newStr += strs[i] + " ";
            }
            System.out.println(newStr);
        }
    }

    /**
     * 描述：
     * <p>
     * 输入一个整数，将这个整数以字符串的形式逆序输出
     * <p>
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     */
    public static void overturnNum() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            String newStr = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                char strChar = str.charAt(i);
                newStr += strChar;
            }
            System.out.println(newStr);
        }
    }

    /**
     * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
     * 输入
     * abaca
     * 输出
     * 3
     */
    public static void acsiiStrNum() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < str.length(); i++) {
                char strChar = str.charAt(i);
                int cNum = strChar;
                if (cNum <= 127 && cNum >= 0) {
                    set.add(cNum);
                }
            }
            System.out.println(set.size());
        }
    }

    /**
     * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
     */
    public static void reReadNum() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            List<Character> list = new ArrayList<>();
            for (int i = str.length() - 1; i >= 0; i--) {
                char strChar = str.charAt(i);
                if (!list.contains(strChar)) {
                    list.add(strChar);
                }

            }
            for (Character character : list) {
                System.out.print(character);
            }
            System.out.println();
        }
    }


    /**
     * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
     * <p>
     * 先输入键值对的个数
     * 然后输入成对的index和value值，以空格隔开
     */
    public static void groupIdx() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            int cnt = Integer.valueOf(str);
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < cnt; i++) {
                String data = b.readLine();
                String[] datas = data.split(" ");
                Integer n1 = Integer.valueOf(datas[0]);
                Integer n2 = Integer.valueOf(datas[1]);

                Integer aNum = map.get(n1);
                if (aNum == null) {
                    map.put(n1, n2);
                } else {
                    map.put(n1, aNum + n2);
                }
            }
            for (Integer integer : map.keySet()) {
                System.out.println(integer + " " + map.get(integer));
            }

        }
    }

    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
     */
    public static void hexToDec() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = b.readLine()) != null) {
            str = str.replaceAll("^0[x|X]", "");
            int num = Integer.parseInt(str, 16);
            System.out.println(num);
        }
    }

    /**
     * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     */
    public static void splitString() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = new String[2];
        for (int i = 0; i < 2; i++) {
            strs[i] = b.readLine();
        }
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int l1 = 8 - str.length() % 8;
            for (int j = 0; l1 != 8 && j < l1; j++) {
                str = str + 0;
            }
            for (int k = 0; k < str.length(); k = k + 8) {
                System.out.println(str.substring(k, k + 8));
            }
        }
    }


    /**
     * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
     * <p>
     * Input Param
     * <p>
     * n               输入随机数的个数
     * <p>
     * inputArray      n个随机整数组成的数组
     * <p>
     * <p>
     * Return Value
     * <p>
     * OutputArray    输出处理后的随机整数
     * <p>
     * <p>
     * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
     * <p>
     * 样例输入解释：
     * 样例有两组测试
     * 第一组是3个数字，分别是：2，2，1。
     * 第二组是11个数字，分别是：10，20，40，32，67，40，20，89，300，400，15。
     */
    public static void randomNumber() throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while ((str = b.readLine()) != null) {
            int n = Integer.parseInt(str);
            int[] num = new int[1001];
            for (int i = 1; i <= n; i++) {
                int h = Integer.parseInt(b.readLine());
                num[h] = h;
            }
            for (int i = 1; i < 1001; i++) {
                if (num[i] != 0)
                    System.out.println(num[i]);
            }
        }
    }

    /**
     * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
     */
    public static void wordNumber() {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        char word = input.next().charAt(0);
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char u1 = Character.toUpperCase(word);
            char u2 = Character.toUpperCase(str.charAt(i));
            if (u1 == u2) {
                num++;
            }
        }
        System.out.println(num);
    }

    /**
     * 计算字符串最后一个单词的长度，单词以空格隔开。
     */
    public static void wordlength() {
        Scanner input = new Scanner(System.in);
        String s = "";
        while (input.hasNextLine()) {
            s = input.nextLine();
            System.out.println(s.length() - 1 - s.lastIndexOf(" "));
        }
    }
}
