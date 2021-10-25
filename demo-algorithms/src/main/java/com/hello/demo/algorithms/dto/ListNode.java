package com.hello.demo.algorithms.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2021.10.25 下午 4:33
 */
public class ListNode {

    public static Function<ListNode, String> listNode2String = listNode -> {
        List<Integer> list = new ArrayList<>();

        while (Objects.nonNull(listNode)) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    };


    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
