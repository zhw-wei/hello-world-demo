package com.hello.demo.algorithms.coding02;

import com.hello.demo.algorithms.dto.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2022/4/18 23:36
 */
@DisplayName("算法08")
public class Hello08 {
    /**
     * leetcode-101 对称二叉树
     *
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    @Test
    @DisplayName("对称二叉树")
    public void test01(){

        //使用循环方式：使用队列存储每一层的节点，循环判断
    }

    //使用递归方式
    private boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return this.deepCheck(root.left, root.right);
    }

    private boolean deepCheck(TreeNode left, TreeNode right){
        //递归的终止条件是两个都是null、有一个是null、两个节点不相等
        //都是null
        if(left == null && right == null) return true;

        //只有一个是null
        if(left == null || right == null) return false;

        //不相等
        if(left.val != right.val) return false;

        //相等时，递归判断下级节点
        return deepCheck(left.left, right.right) && deepCheck(left.right, right.left);
    }

    /**
     * leetcode-104 二叉树最大深度
     *
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    @Test
    @DisplayName("二叉树最大深度")
    public void test02(){

        //队列方式：队列中存储每一层的节点，然后循环遍历每一层的节点
    }

    //递归方式
    private int maxDepth(TreeNode root) {
        if(root == null) return 0;

        //每遍历一次，深度加一
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
