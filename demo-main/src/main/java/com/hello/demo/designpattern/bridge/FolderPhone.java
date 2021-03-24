package com.hello.demo.designpattern.bridge;

//折叠式手机类
public class FolderPhone extends Phone{
    public FolderPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        super.open();
        System.out.println("folder phone");
    }

    public void close(){
        super.close();
        System.out.println("folder phone");
    }

    public void call(){
        super.call();
        System.out.println("folder phone");
    }
}
