package com.hello.scala.leetCode._2020

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._

object Find123pattern extends App {

  println(find132pattern(Array(1, 5)))
  println(find132pattern_2(Array(10, 5, 8, 8, 2, 4)))


  /**
   * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
   * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
   *
   * 注意：n 的值小于15000。
   *
   * @param nums
   * @return
   */
  def find132pattern(nums: Array[Int]): Boolean = {

    var result = false
    breakable {
      for (i <- 0 until nums.length; j <- i until nums.length; k <- j until nums.length) {
        if (k > j && j > i && nums(j) > nums(k) && nums(k) > nums(i)) {
          result = true
          break
        }
      }
    }
    result
  }

  def find132pattern_2(nums: Array[Int]): Boolean = {
    if (nums.length < 3)
      false
    else {

      var result = false
      //保存(j+1 ~ n)之间的数据，且stack中的数据一定比minArray(j)大
      val stack = mutable.Stack[Int]()

      //minArray(j) = （1 ~ j-1）之间最小的数
      val minArray = ArrayBuffer[Int](nums(0))  //保存每一位数之前对应的最小的数
      for (i <- 1 until nums.length) {
        minArray.addOne(Math.min(minArray(i - 1), nums(i)))
      }

      breakable {
        for (j <- (0 until nums.length).reverse) {//倒序遍历
          if (nums(j) > minArray(j)) {
            //每次遍历取值，都是一次全新的判断，所以此处的pop并没有什么问题
            //而且判断的是"存在"，而不是找到具体序列，所以此处的判断逻辑是对的
            while (!stack.isEmpty && stack.top <= minArray(j)) {
              stack.pop
            }
            if (!stack.isEmpty && stack.top < nums(j)) {
              result = true
              break
            }
            stack.push(nums(j))
          }
        }
      }

      result
    }
  }
}
