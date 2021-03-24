package com.hello.demo.designpattern.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * 大学
 */
public class University extends OrganizationComponent{
    private List<OrganizationComponent> components = new ArrayList<>();

    public University(String name, String desc){
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent component) {
        //do something
        this.components.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        //do something
        this.components.remove(component);
    }

    @Override
    protected void print() {
        //do something
        System.out.println("---------------" + this.getName() + "---------------");
        System.out.println(components.stream().map(OrganizationComponent::getName).collect(Collectors.joining(", ")));
    }
}
