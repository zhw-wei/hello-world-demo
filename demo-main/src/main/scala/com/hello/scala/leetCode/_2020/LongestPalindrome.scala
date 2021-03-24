package com.hello.scala.leetCode._2020

import util.control.Breaks._

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
object LongestPalindrome extends App {

  println(longestPalindrome("babad"))


  /**
   * 暴力解法
   */
  def longestPalindrome(s: String): String = {
    var result = s
    if (result.length < 2) {
      result
    } else {

      var maxLength = 1
      var begin = 0
      val charArray = s.toCharArray

      for (i <- 0 until s.length - 1; j <- i + 1 until s.length) {
        if (j - i + 1 > maxLength && validPalindromic(charArray, i, j)) {
          maxLength = j - i + 1
          begin = i
        }
      }

      result = s.substring(begin, begin + maxLength)
      result
    }
  }

  /**
   * 验证子串 s[left..right] 是否为回文串
   */
  def validPalindromic(charArray: Array[Char], inLeft: Int, inRight: Int): Boolean = {
    var left = inLeft
    var right = inRight
    var result = true
    breakable {
      while (left < right) {
        if (charArray(left) != charArray(right)) {
          result = false
          break
        }
        left += 1
        right -= 1
      }
    }
    result
  }

  /**
   * 动态规划 todo
   */

}
