package com.hello.demo.designpattern.decorator;

import lombok.Getter;
import lombok.Setter;

public abstract class Drink {
    @Getter@Setter
    private String des;
    @Getter@Setter
    private Float price = 0.0f;

    public abstract Float cost();
}
