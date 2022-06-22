package com.maxlong.study.algorithm.leetcode;

import java.util.*;

public class SolutionPoker {

    public static final Map<String, Integer> cardMap = new HashMap<>();
    static {
        cardMap.put("3", 3);
        cardMap.put("4", 4);
        cardMap.put("5", 5);
        cardMap.put("6", 6);
        cardMap.put("7", 7);
        cardMap.put("8", 8);
        cardMap.put("9", 9);
        cardMap.put("10", 10);
        cardMap.put("J", 11);
        cardMap.put("Q", 12);
        cardMap.put("K", 13);
        cardMap.put("A", 14);
    }

    public static void main(String[] args) {
        String[] myCards = {"3", "3", "3", "3", "4", "4", "5", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] heapCards = {"4", "5", "6", "7", "8", "8", "8"};
        System.out.println(new SolutionPoker().maxlenthSeq(Arrays.asList(myCards), Arrays.asList(heapCards)));
    }

    public String maxlenthSeq(List<String> myCards, List<String> heapCards){
        Map<String, Integer> cardNums = new HashMap<>();
        for (String myCard : myCards) {
            Integer num = cardNums.getOrDefault(myCard, 0);
            cardNums.put(myCard, num + 1);
        }
        for (String heapCard : heapCards) {
            Integer num = cardNums.getOrDefault(heapCard, 0);
            cardNums.put(heapCard, num + 1);
        }

        List<Integer> playerCards = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cardMap.entrySet()) {
            String cardCode = entry.getKey();
            Integer cardNum = entry.getValue();
            if(cardNums.getOrDefault(cardCode, 0) < 4){
                playerCards.add(cardNum);
            }
        }
        playerCards.sort(Comparator.comparingInt(a -> a));

        int[] seqNums = new int[playerCards.size()];
        for(int  i = 0; i < playerCards.size(); i++){
            if (i > 0 && playerCards.get(i) == playerCards.get(i - 1) + 1 && seqNums[i - 1] < 12) {
                seqNums[i] = seqNums[i - 1] + 1;
            } else {
                seqNums[i] = 1;
            }
        }

        int maxSeqIdx = 0;
        int maxSeqNum = 0;
        for (int i = 0; i < seqNums.length; i++) {
            if(seqNums[i] >= maxSeqNum){
                maxSeqIdx = i;
                maxSeqNum = seqNums[i];
            }
        }

        if(maxSeqNum < 5){
            return "NO-CHAIN";
        }

        Integer charnMaxNum = playerCards.get(maxSeqIdx);
        String result = "";
        for(int i = 0; i < maxSeqNum; i++, charnMaxNum--){
            if(charnMaxNum <= 10){
                result = charnMaxNum + "-" +  result;
            } else if(charnMaxNum == 11){
                result = "J" + "-" + result;
            } else if(charnMaxNum == 12){
                result = "Q" + "-" + result;
            } else if(charnMaxNum == 13){
                result = "K" + "-" + result;
            } else if(charnMaxNum == 14){
                result = "A" + "-" + result;
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
