package com.hello.scala.funcSenior

object Hello51 extends App {
  //元组
  val tup0 = (1, 2, 3, 4, 5, "a", "b", "c")
  val tup1 = Tuple8(1, 2, 3, 4, 5, "a", "b", "c")
  println(tup0)
  println(tup0._1)

  tup0.productIterator.foreach(print(_))
  println
  tup1.productIterator.foreach(print(_))

}
