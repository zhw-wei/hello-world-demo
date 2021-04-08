package com.hello.scala.leetCode._2020

object LengthOfLongestSubstring extends App {

  println(lengthOfLongestSubstring("abcabcbb"))

  /**
   * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
   * 思想转换为：计算两个相同字符的距离
   */
  def lengthOfLongestSubstring(s: String): Int = {

    val array = new Array[Int](128) //记录字符串上一次出现的位置

    var start = 0 //窗口开始位置
    var res = 0
    for (i <- 0 until s.length) {
      val index = s.charAt(i)
      start = Math.max(start, array(index))
      res = Math.max(res, i - start + 1)
      array(index) = i + 1
    }

    res
  }

}
