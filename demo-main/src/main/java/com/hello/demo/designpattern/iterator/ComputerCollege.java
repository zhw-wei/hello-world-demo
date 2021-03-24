package com.hello.demo.designpattern.iterator;

import java.util.Iterator;

public class ComputerCollege implements College {

    private Department[] departments;
    private Integer numberOfDepartment = 0;

    public ComputerCollege(Integer len){
        departments = new Department[len];
    }
    @Override
    public String getName() {
        return "computer";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departments[numberOfDepartment++] = new Department(name, desc);
    }

    @Override
    public ComputerCollegeIterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
