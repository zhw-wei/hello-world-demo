package com.hello.demo.designpattern.command;

public class RemoteController {
    //开关按钮命令数组
    private Command[] onCommand;
    private Command[] offCommand;
    //执行撤销的命令
    private Command undoCommand;

    public RemoteController(){
        onCommand = new Command[5];
        offCommand = new Command[5];

        for(int i=0; i<5; i++){
            onCommand[i] = new NoCommand();
            offCommand[i] = new NoCommand();
        }
    }

    //给按钮设置需要的命令
    public void setCommand(int no, Command onCommand, Command offCommand){
        this.onCommand[no] = onCommand;
        this.offCommand[no] = offCommand;
    }
    //按下开按钮
    public void onButtonPushed(int no){
        //找到按下开的按钮，并调用对应的方法
        onCommand[no].execute();
        //记录本次操作，用于撤销
        undoCommand = onCommand[no];
    }
    //按下关按钮
    public void offButtonPushed(int no){
        offCommand[no].execute();
        undoCommand = offCommand[no];
    }
    //按小撤销按钮
    public void undoButtonPushed(){
        undoCommand.undo();
    }
}
