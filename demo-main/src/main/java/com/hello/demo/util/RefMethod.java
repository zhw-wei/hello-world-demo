package com.hello.demo.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RefMethod {

    public static void main(String[] args) throws Throwable {
        //使用MethodHandle调用方法
        HelloDTO hello = new HelloDTO();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle methodHandle = lookup.findVirtual(HelloDTO.class, "getMerchantNo", methodType);
        System.out.println(methodHandle.invoke(hello));

        System.out.println("==============");
        //使用内省Introspector
        BeanInfo beanInfo = Introspector.getBeanInfo(hello.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor p : propertyDescriptors) {
            Method method = p.getReadMethod();
            System.out.println(method.invoke(hello));
        }

        System.out.println("==============");
        PropertyDescriptor property = new PropertyDescriptor("merchantNo", HelloDTO.class);
        Method method = property.getReadMethod();
        System.out.println(method.invoke(hello));
    }
}
