package com.hello.demo.jvm.bytecode;

/**
 * @author zhw
 * @date 2021/8/31 10:08 下午
 */
class Father{
    int x = 10;

    public Father(){
        this.print();
        x = 20;
    }

    public void print(){
        System.out.println("Father.x=" + x );
    }
}

class Son extends Father{
    int x = 30;
    public Son(){
        this.print();
        x = 40;
    }

    public void print(){
        System.out.println("Son.x=" + x);
    }
}

public class SonTest {
    public static void main(String[] args) {
        Father father = new Father();
        System.out.println(father.x);

        Father father2 = new Son();
        //属性不存在多态性，此处结果是 20
        System.out.println(father2.x);
    }
}
