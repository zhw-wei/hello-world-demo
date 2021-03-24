package com.hello.demo.designpattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sheep implements Cloneable{

    private String name;
    private Integer age;
    private String color;

    //克隆该实例，使用默认的clone方法来完成
    //浅拷贝：直接调用clone方法
    //深拷贝：对象中引用数据类型需要手动clone
    @Override
    protected Sheep clone() throws CloneNotSupportedException {

        Sheep sheep = (Sheep) super.clone();

        return sheep;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep sheep = new Sheep("aa", 1, "bb");
        Sheep sheep1 = sheep.clone();

        System.out.println(sheep);
        System.out.println(sheep1);
    }
}
