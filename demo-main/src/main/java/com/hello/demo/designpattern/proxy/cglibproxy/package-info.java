/**
 * Cglib代理也叫做子类代理，它是在内存中构建一个子类对象，从而实现对目标对象功能扩展<br/>
 * Cglib是一个强大的高性能的代码生成包，它可以在运行期扩展java类与实现java接口，它广泛的被许多AOP框架使用，比如spring aop，实现方法拦截<br/>
 * 在aop编程中如何选择代理模式：1. 目标对象需要实现接口，用jdk代理 2. 目标对象不需要实现接口，用cglib代理<br/>
 * cglib包的底层是通过使用字节码处理框架ASM来转换字节码并生成新的类
 */
package com.hello.demo.designpattern.proxy.cglibproxy;