package com.hello.demo.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://blog.csdn.net/weixin_38055381/article/details/82865385
 *
 * @author: zhaohw
 * @date: 2021/4/8 15:52
 * @description: java11新特性
 */
public class HelloJava11 {

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, ExecutionException {
        HelloJava11 java11 = new HelloJava11();

        java11.test01();
        System.out.println("==============00===============");

        java11.test02();
        System.out.println("==============01===============");

        java11.test03();
        System.out.println("==============02===============");

        java11.test04();
        System.out.println("==============03===============");

        java11.test05();
        System.out.println("==============04===============");

        java11.test06();
    }

    /**
     * 本地变量类型推断
     */
    private void test01() {
        var string = "hello world";
        System.out.println(string);
    }

    /**
     * 字符串加强，新增字符串处理方法
     */
    private void test02() {
        //判断字符串是否是空白
        System.out.println(" ".isBlank());
        //去除首位空格
        System.out.println(" string ".strip());
        //去除尾部空格
        System.out.println(" string ".stripTrailing());
        //去除首部空格
        System.out.println(" string ".stripLeading());
        //复制字符串
        System.out.println(" string ".repeat(3));
        //行数统计
        System.out.println("A\nB\nC".lines().count());
    }

    /**
     * Jdk 里面为集合（List/ Set/ Map）都添加了 of 和 copyOf 方法，它们两个都用来创建不可变的集合
     */
    private void test03() {
        var list = List.of(1, 2, 3, 4, 5);
        var list0 = List.copyOf(list);
        System.out.println(list == list0);

        var list1 = new ArrayList<>();
        var list2 = List.copyOf(list1);
        System.out.println(list1 == list2);
    }

    /**
     * stream加强
     */
    private void test04() {
        //增加单参数构造方法可为null
        System.out.println(Stream.ofNullable(null).count());

        //增加 takeWhile 和 dropWhile 方法
        //takeWhile -- 条件作为区间取值的右区间 -- [0, n] -- 某一个节点作为结束节点
        System.out.println(Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1).takeWhile(i -> i < 3).collect(Collectors.toList()));
        //dropWhile -- 条件作为区间取值的左区间 -- [n, max] -- 某一个点作为开始节点
        System.out.println(Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1).dropWhile(i -> i < 3).collect(Collectors.toList()));
    }

    /**
     * Optional加强，将一个 Optional 转换成一个 Stream, 或者当一个空 Optional 时给它一个替代的
     */
    private void test05(){
        System.out.println(Optional.of("hello world").orElseThrow());
        System.out.println(Optional.of("hello world").stream().count());
        System.out.println(Optional.ofNullable(null).or(() -> Optional.of("hello world")).get());
    }

    /**
     * 增加http工具，源码在java.net包
     */
    private void test06() throws IOException, InterruptedException, TimeoutException, ExecutionException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com"))
                .GET()  // .GET() 可以省略，默认请求方式为 GET
                .build();

        var client = HttpClient.newHttpClient();
        //同步
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        System.out.println("=============================");
        //异步
        Future<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response1 = future.get(100, TimeUnit.SECONDS);
        System.out.println(response1.body());
    }
}
