package com.maxlong.study.algorithm.huawei;

import java.util.*;

/**
 * 描述
 * 考试题目和要点：
 *
 * 1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整等字样填写。
 *
 * 2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。
 *
 * 3、阿拉伯数字中间有“0”时，中文大写要写“零”字，阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如6007.14，应写成“人民币陆仟零柒元壹角肆分“。
 * 4、10应写作“拾”，100应写作“壹佰”。例如，1010.00应写作“人民币壹仟零拾元整”，110.00应写作“人民币壹佰拾元整”
 * 5、十万以上的数字接千不用加“零”，例如，30105000.00应写作“人民币叁仟零拾万伍仟元整”
 *
 * 输入描述：
 * 输入一个double数
 *
 * 输出描述：
 * 输出人民币格式
 *
 * 示例1
 * 输入：
 * 151121.15
 * 输出：
 * 人民币拾伍万壹仟壹佰贰拾壹元壹角伍分
 * 示例2
 * 输入：
 * 1010.00
 * 输出：
 * 人民币壹仟零拾元整
 */
public class HanMoney {

    static Map<Integer, String> numMap = new LinkedHashMap<Integer, String>() {
        {
            put(1, "壹");
            put(2, "贰");
            put(3, "叁");
            put(4, "肆");
            put(5, "伍");
            put(6, "陆");
            put(7, "柒");
            put(8, "捌");
            put(9, "玖");
            put(10, "拾");
        }
    };

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String money = in.nextLine();
            String[] zxFg = money.split("\\.");

            String zsPt = zxFg[0];
            String xsPt = zxFg.length == 2 ? zxFg[1] : "00";

            Integer jiao = Integer.valueOf(xsPt.substring(0, 1));
            Integer fen = Integer.valueOf(xsPt.substring(1));

            String xsName = "";
            if(jiao == 0 && fen == 0){
                xsName = "整";
            } else {
                if (jiao != 0) {
                    xsName = xsName + numMap.get(jiao) + "角";
                }
                if (fen != 0) {
                    xsName = xsName + numMap.get(fen) + "分";
                }
            }

            String zsName = "";
            Integer zs = Integer.valueOf(zsPt);
            int wei = 0;
            while (zs > 0) {
                Integer pt = zs % 10000;
                String namept = findNamePt(pt);
                if(wei == 0){
                    namept = namept + "元";
                } else if(wei == 1){
                    namept = namept + "万";
                } else if(wei == 2){
                    namept = namept + "亿";
                }
                zsName = namept + zsName;
                wei++;
                zs = zs / 10000;
            }
            System.out.println("人民币" + zsName + xsName);
        }
    }

    private static String findNamePt(Integer pt) {
        Integer q = pt / 1000;
        String name = "";
        if(q > 0){
            name = numMap.get(q) + "仟";
        }
        Integer by = pt % 1000;
        if(by == 0){
            return name;
        }

        Integer b = by / 100;
        if(b > 0){
            name = name + numMap.get(b) + "佰";
        } else if (q > 0 && b == 0) {
            name = name + "零";
        }

        Integer sy = by % 100;
        if(sy == 0){
            return name;
        }

        Integer s = sy / 10;
        if(s > 1){
            name = name + numMap.get(s) + "拾";
        } else if(s == 1){
            name = name + "拾";
        } else if (q > 0 && b != 0 &&  s == 0) {
            name = name + "零";
        }

        Integer gy = sy % 10;
        if(gy == 0){
            return name;
        } else {
            name = name + numMap.get(gy);
        }
        return name;
    }

}
