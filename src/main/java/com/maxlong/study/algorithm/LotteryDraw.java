package com.maxlong.study.algorithm;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 2019/10/19.
 *
 * @author MaXiaolong
 */
public class LotteryDraw {

    private static final List<String> prize = Lists.newArrayList("a", "x", "a", "a");

    private static final int simulateNum = 10000;

    public static void main(String[] args) {
        int change = lotteryDraw(simulateNum, true);
        int notChange = lotteryDraw(simulateNum, false);
        System.out.printf("更换盒子中奖次数: %s, 概率：%s\n", change, gailv(change, simulateNum));
        System.out.printf("不更换盒子中奖次数: %s, 概率：%s\n", notChange, gailv(notChange, simulateNum));
    }

    private static int lotteryDraw(int num, boolean change) {
        int winNum = 0;
        for (int i = 0; i < num; i++) {
            List<String> prizeTemp = new ArrayList<>(prize);

            //抽奖人随机选择一个
            String luckyDraw = prizeTemp.remove(ThreadLocalRandom.current().nextInt(prizeTemp.size()));

            //从奖品池中移除目标奖品，可能已经被抽走
            boolean remove = prizeTemp.remove("x");

            //主持人随机选择一个，（目标奖品已经不在了）
            prizeTemp.remove(ThreadLocalRandom.current().nextInt(prizeTemp.size()));

            //如果抽奖者选择重新选择，则随机再抽取
            if (change) {
                //如果上面已经移走，则先恢复
                if(remove)  prizeTemp.add("x");
                //从剩余奖品中选择
                luckyDraw = prizeTemp.remove(ThreadLocalRandom.current().nextInt(prizeTemp.size()));
            }

            //如果抽中。中奖次数加1
            if(luckyDraw.equals("x")){
                winNum =  winNum + 1;
            }
        }
        return winNum;
    }

    private static String gailv(int change, int total) {
        return new BigDecimal(change).divide(new BigDecimal(total), 4, BigDecimal.ROUND_HALF_DOWN).toString();
    }
}
