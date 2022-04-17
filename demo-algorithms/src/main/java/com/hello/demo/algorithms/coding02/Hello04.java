package com.hello.demo.algorithms.coding02;

import com.hello.demo.algorithms.dto.ListNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhw
 * @date 2022/4/17 11:02
 */
@DisplayName("算法04")
public class Hello04 {
    /**
     * leetcode-141 环形链表
     * <p>
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
     * <p>
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     */
    @Test
    @DisplayName("环形链表")
    public void test01() {
        //[3,2,0,-4]
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2;
        System.out.println(this.hasCycle(head));
        System.out.println(this.hasCycle_2(head));
    }

    private boolean hasCycle(ListNode head) {
        if (head == null) return false;
        Set<ListNode> read = new HashSet<>();
        read.add(head);
        while (head.next != null) {
            if (read.contains(head.next)) return true;
            read.add(head.next);
            head = head.next;
        }
        return false;
    }

    //双指针，变成快慢指针追击的问题，如果存在环，那么就会出现指针指向的对象相同的情况
    //快慢指针移动节点速度不同
    private boolean hasCycle_2(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;//一次移动两个节点
        ListNode slow = head;//一次移动一个节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    /**
     * leetcode-142
     * <p>
     * 给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     */
    @Test
    @DisplayName("环形链表2")
    public void test02() {
        //[3,2,0,-4]
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2;
        System.out.println(this.detectCycle(head));

    }

    //快慢指针解法
    //也可以用hashset存储遍历的节点
    private ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode fast = head;//一次移动两个节点
        ListNode slow = head;//一次移动一个节点
        //判断是否有环
        boolean hasCycle = false;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }

        /*
         * 任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍
         * 注意：相遇点不是连接点，此处解法要看leetcode解题说明
         */
        if (hasCycle) {
            //慢指针重置到头部，可以看作一个新的指针
            slow = head;
            while (slow != fast) {
                //快慢指针移动速度变为1
                //当两个指针值相等时，就是环开始的头部
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        return null;
    }

    /**
     * leetcode-160 相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * <p>
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     */
    @Test
    @DisplayName("相交链表")
    public void test03() {
        //listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
        ListNode node4 = new ListNode(4);
        ListNode node1 = new ListNode(1);
        node4.next = node1;

        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node5.next = node6;

        ListNode node8 = new ListNode(8);
        ListNode node42 = new ListNode(4);
        ListNode node52 = new ListNode(5);
        node8.next = node42;
        node42.next = node52;

        node1.next = node8;
        node6.next = node8;

        //双循环嵌套解法此处不表

        System.out.println(this.getIntersectionNode(node4, node5));
        System.out.println(this.getIntersectionNode_2(node4, node5));
        System.out.println(this.getIntersectionNode_3(node4, node5));
    }

    private ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode node0 = headA, node1 = headB;

        //哈希解法，使用额外的空间
        Set<ListNode> set = new HashSet<>();

        while (node0 != null) {
            set.add(node0);
            node0 = node0.next;
        }

        while (node1 != null) {
            if (set.contains(node1)) return node1;
            node1 = node1.next;
        }
        return null;
    }

    private ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        //双指针解法
        ListNode node0 = headA, node1 = headB;

        while (node0 != node1) {
            node0 = node0 == null ? headB : node0.next;
            node1 = node1 == null ? headA : node1.next;
        }
        return node0;
    }

    private ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        //找到两个链表的长度，截断较长的那个链表，然后比较
        ListNode node0 = headA, node1 = headB;

        int len0 = 0, len1 = 0;
        while(node0 != null || node1 != null){
            if(node0 != null){
                len0 ++;
                node0 = node0.next;
            }
            if(node1 != null){
                len1 ++;
                node1 = node1.next;
            }
        }

        //重置到链表头
        node0 = headA;
        node1 = headB;
        if(len0 > len1){
            for(int i=0; i<len0-len1; i++) node0 = node0.next;
        }else if(len0 < len1){
            for(int i=0; i<len1-len0; i++) node1 = node1.next;
        }

        while(node0 != null && node1 != null){
            if(node0 == node1) return node0;
            node0 = node0.next;
            node1 = node1.next;
        }

        return null;
    }


}
