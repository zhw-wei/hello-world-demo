package com.hello.scala.leetCode._2020

import scala.collection.mutable

import util.control.Breaks._

object TowSum extends App {

  twoSum(Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 9)

  /**
   * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
   * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
   */
  def twoSum(nums: Array[Int], target: Int): Unit = {
    println(twoSum_0(nums, target).toList)
    println(twoSum_1(nums, target).toList)
  }

  /**
   * 暴力解法
   */
  def twoSum_0(nums: Array[Int], target: Int): Array[Int] = {

    var array = Array(-1, -1)
    breakable {
      for (i <- 0 to nums.length - 1; j <- 0 to nums.length - 1) {
        if (i != j && nums(i) + nums(j) == target) {
          array = Array(i, j)
          break
        }
      }
    }
    array
  }

  /**
   * 哈希解法
   */
  def twoSum_1(nums: Array[Int], target: Int): Array[Int] = {

    var array = Array(-1, -1)
    val map = mutable.Map[Int, Int]()
    breakable {
      for (i <- 0 to nums.length - 1) {//每一个数只和前面的数相加
        if (map.contains(target - nums(i))) {
          array = Array(i, map.get(target - nums(i)).get)
          break
        }
        map.addOne(nums(i) -> i)
      }
    }

    array
  }
}
