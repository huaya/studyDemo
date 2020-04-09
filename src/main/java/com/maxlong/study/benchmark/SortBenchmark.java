package com.maxlong.study.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import com.maxlong.study.sort.ArrayData;
import com.maxlong.study.sort.Sort;
import com.maxlong.study.sort.SortFactory;

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
public class SortBenchmark {

    private Sort sort;

    private int[] array = ArrayData.array;

    SortFactory.SortType sortType = SortFactory.SortType.BUBBLE;

    @Setup
    public void buildMeCounterHearty() {
        sort = SortFactory.build(sortType);
    }

    @Benchmark
    public void sort() {
        sort.sort(array);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
