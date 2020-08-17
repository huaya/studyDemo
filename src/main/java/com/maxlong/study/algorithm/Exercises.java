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
        binOneNum();
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
