package com.hello.demo.designpattern.command;

public class LightOffCommand implements Command{

    private LightReceiver light;

    public LightOffCommand(LightReceiver light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
