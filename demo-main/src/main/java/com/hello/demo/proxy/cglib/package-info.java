/**
 * 什么是cglib:
 * CGLIB是一个功能强大，高性能的代码生成包。它为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充。
 * 通常可以使用Java的动态代理创建代理，但当要代理的类没有实现接口或者为了更好的性能，CGLIB是一个好的选择。
 *
 * cglib原理：
 * CGLIB 原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。
 *              在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
 * CGLIB 底层：使用字节码处理框架ASM，来转换字节码并生成新的类。
 *              不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
 * CGLIB缺点：对于final方法，无法进行代理。
 *
 * 广泛的被许多AOP的框架使用，例如Spring AOP和dynaop。Hibernate使用CGLIB来代理单端single-ended(多对一和一对一)关联。
 *
 * cglib-nodep-2.2.jar：使用nodep包不需要关联asm的jar包,jar包内部包含asm的类.
 * cglib-2.2.jar：使用此jar包需要关联asm的jar包,否则运行时报错.
 */
package com.hello.demo.proxy.cglib;