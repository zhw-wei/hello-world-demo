package com.hello.demo.algorithms.coding02;

import com.hello.demo.algorithms.dto.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2022/4/21 22:56
 */
@DisplayName("算法09")
public class Hello09 {

    /**
     * leetcode-110 平衡二叉树
     *
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为： 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
     */
    @Test
    @DisplayName("平衡二叉树")
    public void test01(){

    }

    private boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return this.helper(root) != -1;
    }

    private int helper(TreeNode node){
        if(node == null) return 0;

        int left = this.helper(node.left);
        int right = this.helper(node.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    /**
     * leetcode-226 翻转二叉树
     *
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     */
    @Test
    @DisplayName("翻转二叉树")
    public void test02(){

    }

    private TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        this.invertTree(root.left);
        this.invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
