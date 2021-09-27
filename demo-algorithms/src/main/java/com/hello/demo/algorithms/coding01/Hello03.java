package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.stream.events.StartDocument;
import java.util.*;

/**
 * @author: zhaohw
 * @date: 2021.09.26 下午 3:31
 */
@DisplayName("算法03")
public class Hello03 {

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
     * 返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("最接近的三数之和")
    public void test01() {

        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = this.threeSumClosest(nums, target);
        Assertions.assertTrue(result == 2, () -> "result is " + result);
    }

    public int threeSumClosest(int[] nums, int target) {

        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            int first = nums[i];

            int secondIndex = i + 1;
            int lastIndex = nums.length - 1;
            while (secondIndex < lastIndex) {
                int second = nums[secondIndex];
                int last = nums[lastIndex];

                int total = first + second + last;
                if (total == target) return total;

                if (total > target) {
                    lastIndex--;
                } else {
                    secondIndex++;
                }

                result = Math.abs(total - target) < Math.abs(result - target) ? total : result;
            }
        }
        return result;
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("电话号码的字母组合")
    public void test02() {
        System.out.println(this.letterCombinations("23"));
    }

    private Map<Integer, List<Character>> map0 = new HashMap<>() {{
        put(2, Arrays.asList('a', 'b', 'c'));
        put(3, Arrays.asList('d', 'e', 'f'));
        put(4, Arrays.asList('g', 'h', 'i'));
        put(5, Arrays.asList('j', 'k', 'l'));
        put(6, Arrays.asList('m', 'n', 'o'));
        put(7, Arrays.asList('p', 'q', 'r', 's'));
        put(8, Arrays.asList('t', 'u', 'v'));
        put(9, Arrays.asList('w', 'x', 'y', 'z'));
    }};

    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<>();
        if (digits.isEmpty()) return resultList;

        String[] split = digits.split("");

        //上一次循环的编号列表
        List<String> preList = new ArrayList<>();

        for (int i = 0; i < split.length; i++) {
            resultList.clear();

            int num = Integer.parseInt(split[i]);
            List<Character> listChar = map0.get(num);

            for (Character c : listChar) {
                if (!preList.isEmpty()) {
                    for (String s : preList) {
                        resultList.add(s + c);
                    }
                } else {
                    resultList.add(String.valueOf(c));
                }
            }
            preList.clear();
            preList.addAll(resultList);
        }

        return resultList;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     */
    @Test
    @DisplayName("四数之和")
    public void test03() {
        //1,2,3,4,5
        ListNode node0 = new ListNode(1,
                new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        node0 = new ListNode(1, new ListNode(2));
        ListNode node = this.removeNthFromEnd(node0, 1);

        if (Objects.nonNull(node)) {
            System.out.println(node.val);
            while (Objects.nonNull(node.next)) {
                System.out.println(node.next.val);
                node = node.next;
            }
        }
    }

    //约定：单向链表
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        list.add(head);
        while (Objects.nonNull(head.next)) {
            list.add(head.next);
            head = head.next;
        }

        int size = list.size();
        int deleteIndex = size - n;
        ListNode remove = list.remove(deleteIndex);

        if (deleteIndex != 0) {
            list.get(deleteIndex - 1).next = remove.next;
        }
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @DisplayName("")
    @Test
    public void test04() {
        Assertions.assertTrue(this.isValid("{}"));
        Assertions.assertTrue(this.isValid("()[]{}"));
        Assertions.assertFalse(this.isValid("(]"));
        Assertions.assertFalse(this.isValid("([)]"));
        Assertions.assertTrue(this.isValid("{[]}"));


        Assertions.assertTrue(this.isValid2("{}"));
        Assertions.assertTrue(this.isValid2("()[]{}"));
        Assertions.assertFalse(this.isValid2("(]"));
        Assertions.assertFalse(this.isValid2("([)]"));
        Assertions.assertTrue(this.isValid2("{[]}"));

    }

    public boolean isValid(String s) {
        String[] strs = {"()", "{}", "[]"};

        while (s.contains(strs[0]) || s.contains(strs[1]) || s.contains(strs[2])) {
            s = s.replace(strs[0], "");
            s = s.replace(strs[1], "");
            s = s.replace(strs[2], "");
        }
        return s.isEmpty();
    }

    // TODO: 2021.09.27  
    public boolean isValid2(String s) {
        Deque<Character> dequeue = new LinkedList<>();
        Map<Character, Character> map0 = new HashMap<>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        for (int i = 0; i < s.length(); i++) {

            char char0 = s.charAt(i);

            if (char0 == '(' || char0 == '{' || char0 == '[') {
                dequeue.add(char0);
            } else if (char0 == '}' || char0 == ']' || char0 == ')') {
                if (dequeue.isEmpty()) return false;

                if (map0.get(char0).charValue() == dequeue.peek().charValue()) dequeue.remove();
                else return false;
            }
        }
        return dequeue.isEmpty();
    }
}
