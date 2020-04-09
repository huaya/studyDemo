package com.maxlong.study.benchmark;

import com.maxlong.study.io.ByteBufferCompareStreamTest;
import com.maxlong.study.sort.SortFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/4/8.
 *
 * @author MaXiaolong
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
@Warmup(iterations = 1, time = 2)
@Measurement(iterations = 5, time = 2)
public class ByteBufferCompareStreamBenchmark {

    File sourceFile;
    File targetFile;

    @Setup
    public void buildFile() {
        sourceFile = new File("E:\\resources\\maxlong_1g.txt");
        targetFile = new File("E:\\resources\\maxlong_1g_bak.txt");
    }

    @Benchmark
    public void copyBy1Stream() {
        ByteBufferCompareStreamTest.copyByStream(sourceFile, targetFile);
    }

    @Benchmark
    public void copyBy2ByteBuffer() {
        ByteBufferCompareStreamTest.copyByByteBuffer(sourceFile, targetFile);
    }

    @Benchmark
    public void copyBy3MapByteBuffer() {
        ByteBufferCompareStreamTest.copyByMapByteBuffer(sourceFile, targetFile);
    }

    @TearDown
    public void deleteFile() {
        targetFile.delete();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ByteBufferCompareStreamBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
