package com.hello.scala.funcSenior

object Hello57 extends App {

  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val list2 = list.filter(_ % 2 == 0)
  println(list2)

  val list3 = list.map(_ * 2)
  println(list3)

  val total = list.fold(0)((a, b) => a + b)
  println(total)

  val total2 = list.reduce(_ + _)
  println(total2)

  val list_empty = List[Int]()//list为空时调用reduce会抛出异常，可以使用reduceOption方法
  val total3 = list_empty.reduceOption((a, b) => a + b).getOrElse(0)
  println(total3)
}
