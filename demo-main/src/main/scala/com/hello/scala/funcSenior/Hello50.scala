package com.hello.scala.funcSenior

import scala.collection.mutable.ArrayBuffer

object Hello50 extends App {

  //定长数组
  val list = new Array[Int](10)
  list(0) = 1

  val list2 = Array(1, 2, 3, 4, "a", "b", "c", "d")

  def f1[T](list: Array[T]): Unit = for (i <- 0 until list.size) print(s"i = $i, list(i) = ${list(i)} \t")

  f1(list)
  println()
  f1(list2)
  println()

  //变长数组
  val list3 = ArrayBuffer[Int]()
  list3.append(2)
  println(list3)
  list3(0) = 3
  println(list3)
}
