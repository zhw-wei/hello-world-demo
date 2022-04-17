package com.hello.demo.algorithms.coding02;

import com.hello.demo.algorithms.dto.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhw
 * @date 2022/4/17 17:46
 */
@DisplayName("算法07")
public class Hello07 {
    /**
     * leetcode-94 二叉树的中序遍历
     * <p>
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * <p>
     * 中序遍历方式是：左-中-右
     */
    @Test
    @DisplayName("二叉树的中序遍历")
    public void test01() {
        //[1,null,2,3]
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;

        System.out.println(this.inorderTraversal(node1));//1,3,2
        System.out.println(this.inorderTraversal_2(node1));//1,3,2
    }

    //递归方式
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        this.readTree(root, list);
        return list;
    }

    private void readTree(TreeNode node, List<Integer> list) {
        if (node == null) return;
        this.readTree(node.left, list);
        list.add(node.val);
        this.readTree(node.right, list);
    }

    //不实用递归遍历树 - 这种实现方式有可能会被问到
    private List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }

        return list;
    }

    /**
     * leetcode-144 二叉树前序遍历
     * <p>
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     * <p>
     * 前序遍历：中-左-右
     */
    @Test
    @DisplayName("二叉树前序遍历")
    public void test02() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        //递归方式
        System.out.println(this.preorderTraversal(node1));//1,2,3
        //不使用递归，使用栈
    }

    private List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        this.readTreePre(root, list);

        return list;
    }

    private void readTreePre(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        this.readTreePre(node.left, list);
        this.readTreePre(node.right, list);
    }

    /**
     * leetcode-145 二叉树的后序遍历
     *
     * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     *
     * 后序遍历：左-右-中
     */
    @Test
    @DisplayName("二叉树的后序遍历")
    public void test03(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        System.out.println(this.postorderTraversal(node1));//[3,2,1]
    }

    private List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        this.readTreePost(root, list);
        return list;
    }

    private void readTreePost(TreeNode node, List<Integer> list){
        if(node == null) return;
        this.readTreePost(node.left, list);
        this.readTreePost(node.right, list);
        list.add(node.val);
    }

}
