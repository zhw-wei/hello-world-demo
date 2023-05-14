package com.hello.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BizService {
    private int n = 10000;
    private List<Integer> arrayList;
    private List<Integer> linkedList;

    public BizService () {
        arrayList = new ArrayList<>(0);
        linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    /**
     * 扫描array集合，待测试的方法1
     */
    public void scanArray() {
        for (int i = 0; i < n; i++) {
            this.arrayList.get(i);
        }
    }

    /**
     * 扫描list集合，待测试的方法2
     */
    public void scanList() {
        for (int i = 0; i < n; i++) {
            this.linkedList.get(i);
        }
    }

    /**
     * 清空数据
     */
    public void clear() {
        for (int i = 0; i < n; i++) {
            this.arrayList.remove(0);
            this.linkedList.remove(0);
        }
    }
}
