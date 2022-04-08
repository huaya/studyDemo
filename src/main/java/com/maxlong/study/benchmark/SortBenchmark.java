package com.maxlong.study.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import com.maxlong.study.sort.ArrayData;
import com.maxlong.study.sort.Sort;
import com.maxlong.study.sort.SortFactory;

import java.util.Arrays;
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

    private int[] array = ArrayData.createBigArray();

    private int[] copyArray;

    @Setup
    public void initArray() {
        copyArray = Arrays.copyOf(array, array.length);
    }

    @Benchmark
    public void bubbleSort() {
        Sort sort = SortFactory.build(SortFactory.SortType.BUBBLE);
        sort.sort(copyArray);
    }

    @Benchmark
    public void selectionSort() {
        Sort sort = SortFactory.build(SortFactory.SortType.SELECTION);
        sort.sort(copyArray);
    }

    @Benchmark
    public void insertionSort() {
        Sort sort = SortFactory.build(SortFactory.SortType.INSERTION);
        sort.sort(copyArray);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
