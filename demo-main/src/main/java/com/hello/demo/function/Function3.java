package com.hello.demo.function;

@FunctionalInterface
public interface Function3<A, B, C, D> {
    D apply(A a, B b, C c);
}
