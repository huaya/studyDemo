package jmh.counter;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Group)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
public class CounterBenchmark {
    private Counter counter;

    @Param// This will default to running through all the counter types
    CounterFactory.CounterType counterType;

    @Setup
    public void buildMeCounterHearty() {
        counter = CounterFactory.build(counterType);
    }

    @Benchmark
    @Group("rw")
    @GroupThreads(20)
    public void inc() {
        counter.inc();
    }

    @Benchmark
    @Group("rw")
    @GroupThreads(1)
    public long get() {
        return counter.get();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CounterBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
