package com.hello.demo.designpattern.composite;

/**
 * 系，叶子节点，其下面没有子节点
 */
public class Department extends OrganizationComponent{

    protected Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        //do something
        System.out.println("---------------" + this.getName() + "---------------");
    }
}
