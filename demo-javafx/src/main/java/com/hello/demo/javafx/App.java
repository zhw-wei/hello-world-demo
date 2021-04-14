package com.hello.demo.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author: zhaohw
 * @date: 2021/4/13 14:25
 * @description: javafx启动类
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Text text = new Text("hello javafx11");
        text.setFont(Font.font("微软雅黑", FontWeight.BOLD, 20));

        HBox hBox = new HBox();
        hBox.setPrefWidth(400);
        hBox.setPrefHeight(300);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(text);

        Scene scene = new Scene(hBox);

        stage.setScene(scene);
        stage.show();

    }
}
