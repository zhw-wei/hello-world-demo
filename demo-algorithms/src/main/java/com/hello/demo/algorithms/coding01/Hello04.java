package com.hello.demo.algorithms.coding01;

import com.hello.demo.algorithms.dto.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2021.10.25 下午 2:07
 */
public class Hello04 {
    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    @Test
    @DisplayName("升序链表合并")
    public void test01() {

        //输入：l1 = [1,2,4], l2 = [1,3,4] 输出：[1,1,2,3,4,4]
        ListNode node0 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode node1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        Assertions.assertTrue(ListNode.listNode2String.apply(this.mergeTwoLists(node0, node1)).equals("1,1,2,3,4,4"));
        Assertions.assertTrue(ListNode.listNode2String.apply(this.mergeTwoLists2(node0, node1)).equals("1,1,2,3,4,4"));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) return l2;
        if (Objects.isNull(l2)) return l1;

        List<Integer> list = new ArrayList<>();
        while (Objects.nonNull(l1)) {
            list.add(l1.val);
            l1 = l1.next;
        }

        while (Objects.nonNull(l2)) {
            list.add(l2.val);
            l2 = l2.next;
        }

        list.sort(Integer::compareTo);

        ListNode head = new ListNode();
        ListNode next = head;
        for (int i = 0; i < list.size(); i++) {
            next.next = new ListNode(list.get(i));
            next = next.next;
        }

        return head.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) return l2;
        if (Objects.isNull(l2)) return l1;

        ListNode head = new ListNode();
        ListNode node = head;

        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            if (Objects.isNull(l1)) {//l2 != null

                node.next = new ListNode(l2.val);
                l2 = l2.next;
            } else if (Objects.isNull(l2)) {//l1 != null

                node.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {

                if (l1.val > l2.val) {
                    node.next = new ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    node.next = new ListNode(l1.val);
                    l1 = l1.next;
                }

            }
            node = node.next;

        }
        return head.next;
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 有效括号组合需满足：左括号必须以正确的顺序闭合。
     */
    @Test
    @DisplayName("生成成对的括号")
    public void test02() {
        List<Object> list = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        Assertions.assertTrue(this.generateParenthesis(3).containsAll(list));
    }

    private List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        this.backTrack(list, new StringBuilder(), 0, 0, n);

        return list;
    }

    private void backTrack(List<String> list, StringBuilder last, int open, int close, int max) {
        if (last.length() == max * 2) {
            list.add(last.toString());
            return;
        }

        //如果左括号数量不大于 max，我们可以放一个左括号
        //如果右括号数量小于左括号的数量，我们可以放一个右括号
        if (open < max) {
            last.append("(");
            this.backTrack(list, last, open + 1, close, max);
            //恢复添加前的状态
            last.deleteCharAt(last.length() - 1);
        }

        if (close < open) {
            last.append(")");
            this.backTrack(list, last, open, close + 1, max);
            //恢复添加前的状态
            last.deleteCharAt(last.length() - 1);
        }
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    @Test
    @DisplayName("链表合并")
    public void test03() {
        //输入：lists = [[1,4,5],[1,3,4],[2,6]]
        //输出：[1,1,2,3,4,4,5,6]
        ListNode node0 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode node1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode node2 = new ListNode(2, new ListNode(6));
        ListNode[] nodes = {node0, node1, node2};

        Assertions.assertTrue(ListNode.listNode2String.apply(this.mergeKLists(nodes)).equals("1,1,2,3,4,4,5,6"));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<ListNode> list = new ArrayList<>();
        for (ListNode node : lists) if (Objects.nonNull(node)) list.add(node);

        ListNode head = new ListNode();
        ListNode node = head;

        while (!list.isEmpty()) {

            //最小节点（不要使用排序，因为排序会降低效率）
            int index = 0;
            ListNode minNode = list.get(index);
            for (int i = 0; i < list.size(); i++) {
                ListNode listNode = list.get(i);
                if (listNode.val < minNode.val) {
                    minNode = listNode;
                    index = i;
                }
            }

            //设置下级节点
            node.next = new ListNode(minNode.val);
            node = node.next;

            //重置数组
            if (Objects.isNull(minNode.next))
                list.remove(index);
            else
                list.set(index, minNode.next);
        }

        return head.next;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    @Test
    @DisplayName("链表交换相邻节点")
    public void test04() {
        //输入：head = [1,2,3,4]
        //输出：[2,1,4,3]
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        String result = ListNode.listNode2String.apply(this.swapPairs(node));
        Assertions.assertTrue(result.equals("2,1,4,3"));
    }

    private ListNode swapPairs(ListNode head) {
        ListNode baseHead = new ListNode(0, head);

        ListNode last = baseHead;
        ListNode current = head;

        //last-x-current--next-x-three
        while (Objects.nonNull(current) && Objects.nonNull(current.next)) {

            ListNode next = current.next;
            ListNode three = current.next.next;

            last.next = next;
            next.next = current;
            current.next = three;

            last = current;
            current = three;
        }

        return baseHead.next;
    }

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 进阶：
     * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("链表节点反转")
    public void test05() {
        //输入：head = [1,2,3,4,5], k = 2
        //输出：[2,1,4,3,5]
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        Assertions.assertTrue(ListNode.listNode2String.apply(this.reverseKGroup(node, 2)).equals("2,1,4,3,5"));
        //k = 3
        //输出：[3,2,1,4,5]
//        Assertions.assertTrue(ListNode.listNode2String.apply(this.reverseKGroup(node, 3)).equals("3,2,1,4,5"));
        //k = 4
        //输出：[4,3,2,1,5]
        Assertions.assertTrue(ListNode.listNode2String.apply(this.reverseKGroup(node, 4)).equals("4,3,2,1,5"));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }
}
