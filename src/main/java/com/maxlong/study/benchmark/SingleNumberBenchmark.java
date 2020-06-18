package com.maxlong.study.benchmark;

import com.maxlong.study.algorithm.SingleNumber;
import com.maxlong.study.serializable.SerializeCompare;
import com.maxlong.study.sort.ArrayData;
import com.maxlong.study.sort.Sort;
import com.maxlong.study.sort.SortFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019-04-30 11:05
 * 类说明:
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
public class SingleNumberBenchmark {

    private Sort sort;

//    private static int[] array = ArrayData.createSingleNumArray(2 * 100 + 1);
    private static int[] array = {1,1,2,2,3,4,4};

    @Benchmark
    public void singleNumber() {
        SingleNumber.singleNumber(array);
    }

    @Benchmark
    public void singleNumber2() {
        SingleNumber.singleNumber2(array);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SingleNumberBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
