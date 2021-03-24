package com.hello.demo.designpattern.visitor;

public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();

        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
        objectStructure.attach(new Woman());
        objectStructure.attach(new Woman());

        Action successAction = new SuccessAction();
        objectStructure.display(successAction);

        Action failAction = new FailAction();
        objectStructure.display(failAction);
    }
}
