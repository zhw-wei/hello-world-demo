package com.hello.demo;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput) // 吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 结果所使用的时间单位
@State(Scope.Thread) // 每个测试线程分配一个实例
@Fork(2) // Fork进行的数目
@Warmup(iterations = 5) // 先预热1轮
@Measurement(iterations = 2) // 进行2轮测试
public class JmhMainApplication {

    BizService bizService;

    @Setup(Level.Trial) // 初始化方法，在全部Benchmark运行之前进行
    public void init() {
        bizService = new BizService();
    }

    @Benchmark
    public void arrayTraverse() {
        bizService.scanArray();
    }

    @Benchmark
    public void listTraverse() {
        bizService.scanList();
    }

    @TearDown(Level.Trial) // 结束方法，在全部Benchmark运行之后进行
    public void arrayRemove() {
        bizService.clear();
    }

    @Test
    public void test() throws RunnerException{
        Options options = new OptionsBuilder().include(JmhMainApplication.class.getSimpleName()).build();
        new Runner(options).run();
    }
}
