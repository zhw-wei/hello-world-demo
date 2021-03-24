package com.hello.demo.jvm.classLoader;

import java.lang.reflect.Method;

/**
 * @author: zhaohw
 * @date: 2021/3/24 16:06
 * @description:
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        test0(args);
        test1(args);
    }

    private static void test0(String[] args) throws Exception {
        MyClassLoaderParentFirst loader = new MyClassLoaderParentFirst();
        Class testAClass = loader.findClass("com.hello.demo.jvm.classLoader.dto.TestA");
        Method method = testAClass.getDeclaredMethod("main", String[].class);
        method.invoke(null, new Object[]{args});

        System.out.println(String.class.getClassLoader());  //null

        /**
         * 执行结果:
         * TestA: com.hello.demo.jvm.classLoader.MyClassLoaderParentFirst@1e643faf
         * TestB: jdk.internal.loader.ClassLoaders$AppClassLoader@1f89ab83
         * 执行结果说明： TestB使用的仍然是默认的系统加载器，而不是自定义加载器，原因是"双亲委派机制"
         */
    }

    private static void test1(String[] args) throws Exception {
        MyClassLoaderParentFirst2 loader = new MyClassLoaderParentFirst2(Thread.currentThread().getContextClassLoader().getParent());
        Class testAClass = loader.loadClass("com.hello.demo.jvm.classLoader.dto.TestA");
        Method method = testAClass.getDeclaredMethod("main", String[].class);
        method.invoke(null, new Object[]{args});

        System.out.println(String.class.getClassLoader());  //null

        /**
         * 执行结果：
         * TestA: com.hello.demo.jvm.classLoader.MyClassLoaderParentFirst2@1e643faf
         * TestB: com.hello.demo.jvm.classLoader.MyClassLoaderParentFirst2@1e643faf
         */
    }
}
