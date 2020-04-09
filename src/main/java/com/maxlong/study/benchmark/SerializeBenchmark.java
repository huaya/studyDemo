package com.maxlong.study.benchmark;

import com.maxlong.study.io.ByteBufferCompareStreamTest;
import com.maxlong.study.serializable.SerializeCompare;
import com.maxlong.study.serializable.UserInfo;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/4/9.
 *
 * @author MaXiaolong
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Warmup(iterations = 1, time = 2)
@Measurement(iterations = 5, time = 2)
public class SerializeBenchmark {

    private UserInfo userInfo;

    private static final int LOOP = 100000;

    @Setup
    public void buildUserInfo() {
        userInfo = new UserInfo();
        userInfo.setUserId("maxlong");
        userInfo.setUserName("马小龙");
    }

    @Benchmark
    public void jdkSerializable() {
        SerializeCompare.jdkSerializable(LOOP, userInfo);
    }

    @Benchmark
    public void byteArraySerializable() {
        SerializeCompare.byteArraySerializable(LOOP, userInfo);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SerializeBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
