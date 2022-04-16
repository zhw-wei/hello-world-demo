package com.hello.demo.algorithms.coding02;

import com.hello.demo.algorithms.dto.ListNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2022/4/17 00:59
 */
@DisplayName("算法03")
public class Hello03 {
    /**
     * leetcode-21
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    @Test
    @DisplayName("合并两个有序链表")
    public void test01() {

        //输入：l1 = [1,2,4], l2 = [1,3,4] 输出：[1,1,2,3,4,4]
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(ListNode.listNode2String.apply(this.mergeTwoLists(list1, list2)));
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //双指针
        ListNode list = new ListNode();
        ListNode head = list;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val > list2.val) {
                    list.next = new ListNode(list2.val);
                    list2 = list2.next;
                } else {
                    list.next = new ListNode(list1.val);
                    list1 = list1.next;
                }
            } else if (list1 != null) {
                list.next = new ListNode(list1.val);
                list1 = list1.next;
            } else if (list2 != null) {
                list.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            list = list.next;
        }
        return head.next;
    }

    /**
     * leetcode-83
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     */
    @Test
    @DisplayName("删除排序链表中的重复元素")
    public void test02() {
        //输入：head = [1,1,2] 输出：[1,2]
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2)));
        System.out.println(ListNode.listNode2String.apply(this.deleteDuplicates(head)));
        System.out.println(ListNode.listNode2String.apply(this.deleteDuplicates_1(head)));
        //[1,1,2,3,3]
        ListNode head2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        System.out.println(ListNode.listNode2String.apply(this.deleteDuplicates(head2)));
        System.out.println(ListNode.listNode2String.apply(this.deleteDuplicates_1(head2)));
        //[1,1,1]
        ListNode head3 = new ListNode(1, new ListNode(1, new ListNode(1)));
        System.out.println(ListNode.listNode2String.apply(this.deleteDuplicates(head3)));
        System.out.println(ListNode.listNode2String.apply(this.deleteDuplicates_1(head3)));

    }

    private ListNode deleteDuplicates(ListNode head) {
        //创建空的节点
        ListNode node = new ListNode();
        node.next = head;

        ListNode h = node;
        while(node.next != null && node.next.next != null){
            if(node.next.val == node.next.next.val){
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }

        return h.next;
    }
    private ListNode deleteDuplicates_1(ListNode head) {
        //减少空节点创建，增加null判断
        if(head == null) return null;
        ListNode node = head;
        while(node.next!=null){
            if(node.val == node.next.val){
                node.next = node.next.next;
            }else{
                node = node.next;
            }
        }
        return head;

    }
}
