package com.hello.demo.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoCollege implements College{
    private List<Department> departmentList;

    public InfoCollege(){
        departmentList = new ArrayList<>();
    }
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departmentList.add(new Department(name, desc));
    }

    @Override
    public InfoCollegeIterator createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
