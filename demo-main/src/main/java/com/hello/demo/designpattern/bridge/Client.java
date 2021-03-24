package com.hello.demo.designpattern.bridge;

public class Client {
    public static void main(String[] args) {
        Phone phone = new FolderPhone(new XiaoMIBrand());
        phone.open();
        phone.call();
        phone.close();

        System.out.println("======");

        Phone phone_1 = new FolderPhone(new VivoBrand());
        phone_1.open();
        phone_1.call();
        phone_1.close();

        System.out.println("======");

        Phone phone_2 = new UpRightPhone(new XiaoMIBrand());
        phone_2.open();
        phone_2.call();
        phone_2.close();

        System.out.println("======");

        Phone phone_3 = new UpRightPhone(new VivoBrand());
        phone_3.open();
        phone_3.call();
        phone_3.close();
    }
}
