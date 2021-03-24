package com.hello.demo.designpattern.iterator;

import java.util.Iterator;

public class ComputerCollegeIterator implements Iterator {
    private Department[] departments;
    private Integer position = 0;

    public ComputerCollegeIterator(Department[] departments){
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if(position>=departments.length || departments[position] == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Object next() {
        return departments[position++];
    }

    @Override
    public void remove() {
        //todo
    }
}
