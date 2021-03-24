package com.hello.demo.designpattern.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学院
 */
public class College extends OrganizationComponent{

    private List<OrganizationComponent> components = new ArrayList<>();

    protected College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent component) {
        //do something
        components.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        //do something
        components.remove(component);
    }

    @Override
    protected void print() {
        //do something
        System.out.println("---------------" + this.getName() + "---------------");
        System.out.println(components.stream().map(OrganizationComponent::getName).collect(Collectors.joining(", ")));
    }
}
