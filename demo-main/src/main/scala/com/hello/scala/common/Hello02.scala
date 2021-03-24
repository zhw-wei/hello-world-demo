package com.hello.scala.common

import util.control.Breaks._

object Hello02 {
  def main(args: Array[String]): Unit = {
    val list = List("aa", 2, 1.5)
    for (i <- list) {
      println(i)
    }

    println("===================")

    for (i <- 1 to 3) { //[0, 3]
      println(i)
    }

    println("===================")

    for (i <- 1 until 3) { //[0, 3)
      println(i)
    }

    println("===================")

    for (i <- 1 to 3 if i != 2) { //循环守卫
      println(i)
    }
    println("===================")
    for (i <- 1 to 3) {
      if (i != 2) {
        println(i)
      }
    }

    println("===================")

    for (i <- 1 to 3; j = i * 2) { //引入变量
      println(j)
    }
    println("===================")
    for (i <- 1 to 3) {
      val j = i * 2
      println(j)
    }

    println("===================")

    for (i <- 1 to 3; j <- 1 to 3) { //循环嵌套
      println(s"i = $i, j = $j")
    }
    println("===================")
    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        println(s"i = $i, j = $j")
      }
    }
    println("===================")
    for {
      i <- 1 to 3
      j <- 1 to 3
    } {
      println(s"i = $i, j = $j")
    }

    println("===================")

    val list1 = for (i <- 1 to 10) yield i
    val list2 = for (i <- 1 to 10) yield i * 2
    val list3 = for (i <- 1 to 10) yield {
      print(i)
      if (i % 2 == 0) i else i * 2
    }
    println(list1)
    println(list2)
    println(list3)

    println("===================")
    for (i <- Range(1, 10, 2)) { //遍历1~10，步长2
      println(i)
    }

    println("===================")
    var a = 1
    while (a < 3) {
      println(a)
      a += 1
    }

    println("===================")
    var b = 1
    breakable { //处理异常
      while (b < 3) {
        if (b == 2) {
          break() //抛出异常
        }
        println(b)
        b += 1
      }
    }
  }
}
