package com.hello.demo.designpattern.composite;

import lombok.Getter;
import lombok.Setter;

//组织
public abstract class OrganizationComponent {
    @Getter@Setter
    private String name;
    @Getter@Setter
    private String desc;

    protected OrganizationComponent(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    /** 部分子类不需要调用add/remove方法，所以不声明成abstract方法 */
    protected void add(OrganizationComponent component){
        throw new UnsupportedOperationException("add error");
    }

    protected void remove(OrganizationComponent component){
        throw new UnsupportedOperationException("remove error");
    }

    protected abstract void print();
}
