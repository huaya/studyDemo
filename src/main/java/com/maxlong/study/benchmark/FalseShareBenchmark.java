package com.maxlong.study.benchmark;

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
 * @version 创建时间：2019/4/17 13:53
 * 类说明:
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class FalseShareBenchmark {

    private long[][] array;

    @Param(value = "1024")
    int LINE_NUM;

    @Param(value = "1024")
    int COLUM_NUM;

    @Setup
    public void initArray() {
        array = new long[LINE_NUM][COLUM_NUM];
    }

    /**
     * 跳跃式访问数组元素的，而不是顺序的，这破坏了程序访问的局部性原理，并且cache是有容量控制的，
     * cache满了会根据一定淘汰算法替换cache行，会导致从内存置换过来的cache行的元素还没等到读取就被替换掉了
     */
    @Benchmark
    public void nocache() {
        for (int i = 0; i < COLUM_NUM; ++i) {
            for (int j = 0; j < LINE_NUM; ++j) {
                array[j][i] = i * 2 + j;
            }
        }
    }

    /**
     * 顺序访问数组里面元素时候，如果当前元素在cache没有命中，
     * 那么会从主内存一下子读取后续若干个元素到cache，也就是一次访问内存可以让后面多次直接在cache命中。
     */
    @Benchmark
    public void cache() {
        for(int i =0;i<LINE_NUM;++i){
            for(int j=0;j<COLUM_NUM;++j){
                array[i][j] = i*2+j;
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FalseShareBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
