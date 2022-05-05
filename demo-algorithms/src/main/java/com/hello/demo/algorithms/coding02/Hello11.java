package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2022/5/5 22:54
 */
@DisplayName("算法11")
public class Hello11 {
    /**
     * leetcode-704 二分查找
     * 给定一个n个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     */
    @Test
    @DisplayName("二分查找")
    public void test01(){
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Assertions.assertEquals(this.search(nums, 2), 1);
        Assertions.assertEquals(this.search(nums, 5), 4);

        int[] nums2 ={-1, 0, 3, 5, 9, 12};
        Assertions.assertEquals(this.search(nums2,9), 4);
    }

    private int search(int[] nums, int target) {
        int start = 0, end = nums.length-1;

        while(start <= end){
            int mid = (start + end) / 2;

            if(nums[mid] < target){
                start = mid + 1;
            }else if(nums[mid] > target){
                end = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
