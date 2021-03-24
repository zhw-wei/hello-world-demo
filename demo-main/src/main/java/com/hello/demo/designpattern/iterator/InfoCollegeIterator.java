package com.hello.demo.designpattern.iterator;

import java.util.Iterator;
import java.util.List;

public class InfoCollegeIterator implements Iterator {

    private List<Department> departmentList;
    private Integer index = -1;

    public InfoCollegeIterator(List<Department> departmentList){
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        if(index >= departmentList.size()-1) {
            return false;
        }else {
            index++;
            return true;
        }
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }
}
