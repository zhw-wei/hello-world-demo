package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhaohw
 * @date: 2021.09.08 下午 3:00
 */
@DisplayName("算法01")
public class Hello01 {

    @Test
    @DisplayName("斐波那契数列+缓存")
    public void test01() {

        //0 1 1 2 3 5 8
        Assertions.assertTrue(this.fibonacci(0) == 0);
        Assertions.assertTrue(this.fibonacci(1) == 1);
        Assertions.assertTrue(this.fibonacci(2) == 1);
        Assertions.assertTrue(this.fibonacci(3) == 2);
        Assertions.assertTrue(this.fibonacci(4) == 3);
        Assertions.assertTrue(this.fibonacci(5) == 5);
        Assertions.assertTrue(this.fibonacci(6) == 8);

        //打印时间，查看计算耗时
        //如果没有使用缓冲，计算时间会很长
        System.out.println(System.currentTimeMillis());
        System.out.println(this.fibonacci(100));
        System.out.println(System.currentTimeMillis());
    }

    private Map<Integer, Long> cache = new HashMap<>();

    private long fibonacci(int i) {
        //同样可以把i=0/1的结果放入cache中
        if (i == 0) return 0;
        if (i == 1) return 1;

        //缓存每次计算的结果
        //f(x) = f(x-1) + f(x-2) 此表达式说明每次每个数字的斐波那契数列都会被计算两次，所以增加缓存防止重复计算
        if (cache.containsKey(i)) return cache.get(i);

        long value = fibonacci(i - 1) + fibonacci(i - 2);
        cache.put(i, value);
        return value;
    }

    /**
     * 给定一个数组和一个目标和，从数组中找两个数字相加等于目标和，输出这两个数字的下标
     * 限制：数组内容不能重复
     */
    @Test
    @DisplayName("数组-目标和")
    public void test02() {

        int[] array = {2, 7, 11, 15};
        int target = 9;

        //实现 1
        int[] result0 = this.test02_0(array, target);
        Assertions.assertTrue(result0[0] == 0);
        Assertions.assertTrue(result0[1] == 1);

        //实现 2
        int[] result1 = this.test02_1(array, target);
        Assertions.assertTrue(result1[0] == 0);
        Assertions.assertTrue(result1[1] == 1);

    }

    private int[] test02_0(int[] array, int target) {

        //创建一个map存储数组值和对应下标
        //循环map的key
        //结果：key index, (target-key) index

        //key: value, value: index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) map.put(array[i], i);

        int index0 = -1;
        int index1 = -1;

        for (Integer value : map.keySet()) {
            int i0 = map.get(value);
            if (map.containsKey(target - value) && map.get(target - value) != i0) {
                index0 = i0;
                index1 = map.get(target - value);
                break;
            }
        }

        //保证结果有序，方便判断
        return new int[]{Math.min(index0, index1), Math.max(index0, index1)};
    }

    private int[] test02_1(int[] array, int target) {

        int index0 = -1;
        int index1 = -1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int value = array[i];

            int i0 = i;

            if (map.containsKey(target - value)) {
                index0 = i0;
                index1 = map.get(target - value);
                break;
            }

            //限制：下标结果的顺序是反的，举例：（9-2=7，2在前，7在后），在读取2是，7并不在缓存中
            //优点：减少一次放入缓存的循环
            map.put(value, i);
        }

        return new int[]{Math.min(index0, index1), Math.max(index0, index1)};
    }

    /**
     * 两个链表表示的数相加，这样就可以实现两个很大的数相加了，无需考虑数值 int ，float 的 限制了。
     * 链表每个节点的值：0~9
     */
    @Test
    @DisplayName("链表表示的数相加")
    public void test03() {

        //把链表转成数字
        Function<Node, Integer> node2Number = node -> {
            StringBuilder sb = new StringBuilder();

            Node currentNode = node;
            while (Objects.nonNull(currentNode)) {
                sb.append(currentNode.node);
                currentNode = currentNode.next;
            }
            return Integer.valueOf(sb.reverse().toString());
        };

        //低位 ~ 高位
        //(2 -> 4 -> 3) + (5 -> 6 -> 4) = (7 -> 0 -> 8)
        //342 + 465 = 807
        Node node00 = new Node(2);
        Node node01 = new Node(4);
        Node node02 = new Node(3);
        node00.next = node01;
        node01.next = node02;

        Node node10 = new Node(5);
        Node node11 = new Node(6);
        Node node12 = new Node(4);
        node10.next = node11;
        node11.next = node12;

        Assertions.assertTrue(node2Number.apply(this.test03_0(node00, node10)) == 807);

        //1342 + 465 = 1807
        Node node03 = new Node(1);
        node02.next = node03;

        Assertions.assertTrue(node2Number.apply(this.test03_0(node00, node10)) == 1807);

        //1342 + 9465 = 10807
        Node node13 = new Node(9);
        node12.next = node13;

        Assertions.assertTrue(node2Number.apply(this.test03_0(node00, node10)) == 10807);

    }

    class Node {
        private int node;
        private Node next;

        public Node(int node) {
            Assertions.assertTrue(node >=0 && node <= 9);
            this.node = node;
        }
    }

    public Node test03_0(Node node00, Node node10) {
        //null节点，链表长度不一致时用到该值
        Node defaultNode = new Node(0);

        //当前节点
        Node startNode0 = node00;
        Node startNode1 = node10;

        //结果
        Node resultHead = new Node(0);
        //结果-当前节点
        Node currentHead = resultHead;
        while (Objects.nonNull(startNode0) || Objects.nonNull(startNode1)) {

            if (Objects.isNull(startNode0)) startNode0 = defaultNode;
            if (Objects.isNull(startNode1)) startNode1 = defaultNode;

            currentHead.node += startNode0.node + startNode1.node;

            startNode0 = startNode0.next;
            startNode1 = startNode1.next;

            //进位：最大1
            currentHead.next = new Node(currentHead.node / 10);
            currentHead.node = currentHead.node % 10;
            currentHead = currentHead.next;
        }

        return resultHead;
    }

    @Test
    @DisplayName("数字反转")
    public void test04(){
        int x = -123;
        Assertions.assertTrue(this.reverse(x) == -321);

        x = -2147483648;
        System.out.println(this.reverse(x));

    }

    public int reverse(int x) {
        long y = x;
        boolean negative = false;
        if(y<0){
            negative = true;
            y = Math.abs(y);
        }

        //字符串反转
        StringBuilder sb = new StringBuilder().append(y).reverse();
        Long value = Long.valueOf(sb.toString()) * (negative ? -1 : 1);
        if(Math.abs(value) > Integer.MAX_VALUE) return 0;
        return value.intValue();
    }

    @Test
    @DisplayName("字符串转换成一个 32 位有符号整数")
    public void test05(){
        Assertions.assertTrue(this.myAtoi("42") == 42);
        Assertions.assertTrue(this.myAtoi("   -42") == -42);
        Assertions.assertTrue(this.myAtoi("4193 with words") == 4193);
        Assertions.assertTrue(this.myAtoi("-91283472332") == -2147483648);
        Assertions.assertTrue(this.myAtoi("  0000000000012345678") == 12345678);
        Assertions.assertTrue(this.myAtoi("00000-42a1234") == 0);
    }

    public int myAtoi(String str){
        if(str.isEmpty()) return 0;

        Pattern pattern = Pattern.compile("[-\\+]?\\d+");
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()){
            String firstMatch = matcher.group(0);
            if(str.trim().indexOf(firstMatch) == 0){
                boolean lessZero = firstMatch.charAt(0) == '-' ? true : false;

                StringBuilder sb = new StringBuilder(firstMatch);
                while(sb.charAt(0) == '+' || sb.charAt(0) == '-' || sb.charAt(0) == '0'){
                    sb.deleteCharAt(0);
                    if(sb.length() == 0) return 0;
                }

                String numberStr = sb.toString();
                if(numberStr.length() > 12)
                    return lessZero ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                Long value = Long.valueOf(numberStr) * (lessZero ? -1 : 1);
                if(value > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if(value < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                return value.intValue();
            }
        }
        return 0;
    }
}
