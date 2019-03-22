package com.maxlong.study.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-21 11:09
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 2, 7, 17, 5, 6, 29);

        List<Integer> list1 = primes.stream().distinct().collect(Collectors.toList());
        System.out.println(list1);

        List<Integer> list2 = primes.stream().distinct().filter(integer -> integer%2==0).map(integer -> integer * integer).collect(Collectors.toList());
        System.out.println(list2);

        IntSummaryStatistics statistics =primes.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());
    }
}
