package com.hello.demo.designpattern.command;

public class Client {
    public static void main(String[] args) {
        LightReceiver lightReceiver = new LightReceiver();

        Command onCommand = new LightOnCommand(lightReceiver);
        Command offCommand = new LightOffCommand(lightReceiver);

        RemoteController controller = new RemoteController();

        //设置遥控器命令，no=0电灯开关操作
        controller.setCommand(0, onCommand, offCommand);

        controller.onButtonPushed(0);
        controller.offButtonPushed(0);
        controller.undoButtonPushed();
    }
}
