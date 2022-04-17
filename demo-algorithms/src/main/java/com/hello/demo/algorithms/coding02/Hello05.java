package com.hello.demo.algorithms.coding02;

import com.hello.demo.algorithms.dto.ListNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2022/4/17 15:14
 */
@DisplayName("算法05")
public class Hello05 {
    /**
     * leetcode-206 反转链表
     * <p>
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    @Test
    @DisplayName("反转链表")
    public void test01() {
        //[1,2,3,4,5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(ListNode.listNode2String.apply(this.reverseList(head)));
        System.out.println(ListNode.listNode2String.apply(this.reverseList_1(head)));
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode node = head;
        ListNode node0 = new ListNode(head.val);

        while (node.next != null) {
            ListNode node1 = new ListNode(node.next.val);
            node1.next = node0;
            node0 = node1;
            node = node.next;
        }
        return node0;
    }

    //减少null判断，不再创建新的对象
    //此方法会修改原来的链表结构
    private ListNode reverseList_1(ListNode head) {
        ListNode curr = head;
        ListNode preNode = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr = next;
        }
        return preNode;
    }

    /**
     * leetcode-234 回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    @Test
    @DisplayName("回文链表")
    public void test02() {
        //[1,2,2,1]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println(this.isPalindrome(head));
        //[1,2]
        ListNode head2 = new ListNode(1, new ListNode(2));
        System.out.println(this.isPalindrome(head2));
    }

    /*
     * 进阶解法：可以使用快慢指针反转后半段链表
     * 快指针一次跳动两个节点，慢指针一次跳动一个节点
     * 当快指针到达尾部时，慢指针刚好到达中间节点
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        //链表反转，且不修改原有链表
        ListNode node = head;
        ListNode node0 = new ListNode(head.val);

        while (node.next != null) {
            ListNode node1 = new ListNode(node.next.val);
            node1.next = node0;
            node0 = node1;
            node = node.next;
        }

        //比较链表
        node = head;
        while (node0 != null) {
            if (node0.val != node.val) return false;
            node = node.next;
            node0 = node0.next;
        }
        return true;
    }

    /**
     * leetcode-876 链表的中间节点
     * <p>
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     */
    @Test
    @DisplayName("链表的中间节点")
    public void test03() {
        //[1,2,3,4,5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(ListNode.listNode2String.apply(this.middleNode(head)));//3,4,5
        System.out.println(ListNode.listNode2String.apply(this.middleNode(head.next)));//4,5
        System.out.println(ListNode.listNode2String.apply(this.middleNode(head.next.next)));//4,5
        System.out.println(ListNode.listNode2String.apply(this.middleNode(head.next.next.next)));//5

    }

    public ListNode middleNode(ListNode head) {
        //快慢指针，快指针跳转两个节点，慢指针跳转一个节点，当快指针到达尾部时，慢指针刚到达中间节点
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点时倒数第一个节点
     * <p>
     * 例如：一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6，链表的倒数第三个节点值为4的节点
     * <p>
     * 约束：k是正整数，且不大于链表的长度
     */
    @Test
    @DisplayName("链表中倒数第k个节点")
    public void test04() {

        //解法1：哈希表存储节点和节点位置，key节点位置，value节点，同时要知道链表的长度
        //解法2：数组存储节点，根据下标取值
        //解法3：两次遍历链表，第一次遍历找到链表的长度，第二次遍历找到节点位置(n-k+1)
        //解法4：使用快慢之之，快指针第一次移动k-1个节点，此后，快慢指针每次都移动一个节点，当快指针到达尾部时，慢指针刚好到达要取的节点
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(ListNode.listNode2String.apply(this.getLastNode(head, 1)));//5
        System.out.println(ListNode.listNode2String.apply(this.getLastNode(head, 2)));//4, 5
    }

    private ListNode getLastNode(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 1; i <= k; i++) {
            if (fast != null) {
                fast = fast.next;
            }
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
