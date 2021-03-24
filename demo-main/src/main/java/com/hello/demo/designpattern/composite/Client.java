package com.hello.demo.designpattern.composite;

public class Client {

    public static void main(String[] args) {
        //大学
        OrganizationComponent university = new University("001 university", "001 university desc");

        //学院
        OrganizationComponent college = new College("001 collete", "001 college desc");
        OrganizationComponent college_1 = new College("002 college", "002 college desc");

        //学院下的系
        OrganizationComponent department = new Department("001 department", "001 department desc");
        OrganizationComponent department_1 = new Department("002 department", "002 department desc");
        OrganizationComponent department_2 = new Department("003 department", "003 department desc");
        OrganizationComponent department_3 = new Department("004 department", "004 department desc");

        OrganizationComponent department_4 = new Department("005 department", "005 department desc");
        OrganizationComponent department_5 = new Department("006 department", "006 department desc");
        OrganizationComponent department_6 = new Department("007 department", "007 department desc");
        OrganizationComponent department_7 = new Department("008 department", "008 department desc");

        //绑定关系1
        college.add(department);
        college.add(department_1);
        college.add(department_2);
        college.add(department_3);

        university.add(college);

        //绑定关系2
        college_1.add(department_4);
        college_1.add(department_5);
        college_1.add(department_6);
        college_1.add(department_7);

        university.add(college_1);

        university.print();
        college.print();
        department.print();
    }
}
