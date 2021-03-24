package com.hello.demo.designpattern.iterator;

public class Client {
    public static void main(String[] args) {
        ComputerCollege college = new ComputerCollege(5);
        college.addDepartment("001", "001");
        college.addDepartment("002", "002");
        college.addDepartment("003", "003");
        college.addDepartment("004", "004");
        college.addDepartment("005", "005");

        ComputerCollegeIterator iterator = college.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
