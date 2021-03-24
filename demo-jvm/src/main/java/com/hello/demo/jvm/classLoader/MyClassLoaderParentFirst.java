package com.hello.demo.jvm.classLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhaohw
 * @date: 2021/3/24 15:57
 * @description:
 */
public class MyClassLoaderParentFirst extends ClassLoader {
    private Map<String, String> classPathMap = new HashMap<>();

    public MyClassLoaderParentFirst() {
        classPathMap.put("com.hello.demo.jvm.classLoader.dto.TestA",
                "D:\\my-projects\\project-java\\hello-world-util\\demo-jvm\\target\\classes\\com\\hello\\demo\\jvm\\classLoader\\dto\\TestA.class");
        classPathMap.put("com.hello.demo.jvm.classLoader.dto.TestB",
                "D:\\my-projects\\project-java\\hello-world-util\\demo-jvm\\target\\classes\\com\\hello\\demo\\jvm\\classLoader\\dto\\TestB.class");
    }

    /**
     * 重写findClass
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String classPath = classPathMap.get(name);
        File file = new File(classPath);
        if (!file.exists())
            throw new ClassNotFoundException();

        byte[] classBytes = this.getClassData(file);

        if (classBytes == null || classBytes.length == 0)
            throw new ClassNotFoundException();

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    //读取文件
    private byte[] getClassData(File file) {
        try (InputStream ins = new FileInputStream(file); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4069];
            int byteNumRead = 0;
            while ((byteNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, byteNumRead);
            }
            return baos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
