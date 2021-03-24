package com.hello.demo.proxy.jdkProxy;

/**
 * 实现的代码和代码最终执行的逻辑并不是100%的对应关系，此处代理模式就是很好的证明
 * 执行类的方法时，会自动调用代理类的invoke方法，此处实现时在编译后生成了一个新的类，所有的方法和代理，是通过新的类执行的
 */
public class Main {
    public static void main(String[] args) {
        JdkProxyInterface jdkProxyInterface = (JdkProxyInterface) new ProxyObject(new JdkProxyObject()).getObject();
        jdkProxyInterface.helloWorld();

        System.out.println("------------------01-----------------");

        JdkProxyInterface in = (JdkProxyInterface) new ProxyObject(new JdkProxyObject()).getObject();
        in.hello();

        System.out.println("------------------02-----------------");

        JdkProxyInterface in2 = new ProxyObjectV<JdkProxyInterface>(new JdkProxyObject()).getObject();
        in2.world();
    }
}
