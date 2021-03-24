/**
 * 动态代理<br/>
 * 1. 代理对象不需要实现接口，但是目标对象要实现接口，否则不能动态代理<br/>
 * 2. 代理对象的生成是利用JDK的API，动态的在内存中构建代理对象<br/>
 * 3. 动态代理也叫做JDK代理、接口代理<br/>
 * JDK中生成代理对象的api<br/>
 * 1. 代理类所在的包：java.lang.reflect.Proxy<br/>
 * 2. jdk实现代理只需要使用newProxyInstance方法，但是该方法需要接收三个参数
 */
package com.hello.demo.designpattern.proxy.dynamicproxy;