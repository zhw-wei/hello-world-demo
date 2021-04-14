package com.hello.demo.javafx;

import javafx.application.Application;

/**
 * @author: zhaohw
 * @date: 2021.04.14 上午 11:21
 * @description: 新增main对象，因为App中直接写main函数启动失败
 * 命令行启动方式：java -jar xxx.jar
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(App.class);
    }
}
