package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 说明： 此文件中的所有排序算法都是生序排序
 *
 * @author zhw
 * @date 2022/4/21 23:13
 */
@DisplayName("算法10")
public class Hello10 {

    /**
     * 冒泡排序
     */
    @Test
    @DisplayName("冒泡排序")
    public void test01() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        this.sort01(nums);
    }

    private void sort01(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        //循环数组长度的次数
        //每次外层循环都会把最大的数值放到数组的尾部
        for (int i = 0; i < nums.length; i++) {
            //从第0个元素开始，一次和后面的元素比较
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j + 1] < nums[j]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    @Test
    @DisplayName("选择排序")
    public void test02() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        this.sort02(nums);
    }

    /**
     * 选择排序：该算法可以看做冒泡排序的优化
     * <p>
     * 1. 找到数组中最大的那个元素
     * 2. 将它和数组中的最后一个元素交换位置
     * 3. 重复步骤2，同时查找最大元素的数组长度逐步减小，最终遍历整个数组
     */
    private void sort02(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int lastIndex = nums.length - 1;
        while (lastIndex >= 0) {
            //找到最大值下标
            int maxIndex = lastIndex;
            for (int i = 0; i < lastIndex; i++) {
                if (nums[maxIndex] < nums[i]) maxIndex = i;
            }

            //最大值放至尾部
            int temp = nums[maxIndex];
            nums[maxIndex] = nums[lastIndex];
            nums[lastIndex] = temp;

            //减少循环长度
            lastIndex--;
        }
    }

    /**
     * jdk使用快速排序、插入排序作为排序算法
     * 排序数组大于或等于286时使用快速排序，小于286时使用插入排序
     */
    @Test
    @DisplayName("插入排序")
    public void test03() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        this.sort03(nums);
    }

    /**
     * 插入排序
     * 将有序数组的下一个值和数组比较放入数组
     * <p>
     * 对于未排序数据，在已排序序列中从后向前开始扫描，找到相应的位置插入
     * 为了给要插入的元素腾出空间，我们需要将插入位置之后的已排序元素都向右移动一位
     */
    private void sort03(int[] nums) {
        int endIndex = 0;

        while (endIndex <= nums.length - 2) {
            //有序顺序列下一个值插入到顺序列中
            int preIndex = endIndex;
            int nextIndex = preIndex + 1;

            //元素右移，即与前一个值交换位置
            while (nums[preIndex] > nums[nextIndex] && preIndex >= 0) {
                int temp = nums[preIndex];
                nums[preIndex] = nums[nextIndex];
                nums[nextIndex] = temp;
                preIndex--;
                nextIndex--;
            }
            System.out.println(Arrays.toString(nums));

            //增加循环长度
            endIndex++;
        }
    }

    @Test
    @DisplayName("快速排序")
    public void test04() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        this.sort04(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 快速排序
     * <p>
     * jdk内置排序算法就是快速排序
     * <p>
     * 此算法采用分治思想
     * <p>
     * 首先任意选取一个数据作为关键数据，我们称为基准数，然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面, 这个过程称为一趟快速排序，也称为分区操作
     * 然后再按此方法对这两部分数分别进行快速排序，整个排序过程可以递归进行，以此达到这个数据变成有序序列
     */
    private void sort04(int[] nums) {
        this.quickSort(nums, 0, nums.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //根据基准分类数据
            int partitionIndex = partition(arr, left, right);
            //基准值左侧分类
            quickSort(arr, left, partitionIndex - 1);
            //基准值右侧分欸
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            //将比基准值小的数放在前面
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        //将基准值放在中间，完成分类
        swap(arr, pivot, index - 1);
        //返回基准值下标
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        //交换值
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    @DisplayName("希尔排序")
    public void test05() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        this.sort05(nums);
    }

    /**
     *
     */
    private void sort05(int[] nums) {

    }

    @Test
    @DisplayName("归并排序")
    public void test06() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        System.out.println(Arrays.toString(this.sort06(nums)));
    }

    /**
     * 对于给定的一组数据，利用递归与分治技术将数据序列划分成为越来越小的半子表，再对半子表排序后，
     * 再用递归方法将排好序的半子表合并成为越来越大的有序序列
     */
    public int[] sort06(int[] nums) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(nums, nums.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort06(left), sort06(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    @Test
    @DisplayName("堆排序")
    public void test07() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        this.sort07(nums);
    }

    /**
     * 二叉堆，是一种完全二叉树的结构，同时满足堆的性质：即子节点的键值或索引总是小于（或大于）它的父节点
     * 在一个二叉堆中，根节点总是最大（或最小）节点，这样的堆我们称之为最大（小）堆
     * <p>
     * 堆排序算法就是抓住了这一节点，每次都取堆顶的元素，然后将剩余的元素重新调整为最大（最小）堆，依次类堆，最终得到排序的序列
     */
    private void sort07(int[] nums) {

    }

    /**
     * 计数排序、基数排序、桶排序三种排序算法都利用了桶的概念
     */
}