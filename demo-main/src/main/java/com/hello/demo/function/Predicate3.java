package com.hello.demo.function;

@FunctionalInterface
public interface Predicate3<A, B, C>{
    Boolean test(A a, B b, C c);
}
