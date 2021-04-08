package com.hello.scala.funcSenior

object Hello52 extends App {

  val list0 = List(1, 3, 4, 5)
  val list1 = list0 :+ 2
  val list2 = 6 +: list0
  println(list0)
  println(list1)
  println(list2)

  val list3 = 1 :: 2 :: 3 :: 4 :: Nil
  val list4 = 1 :: 2 :: -1 :: list3
  val list5 = list3 :: 1 :: 2 :: Nil
  val list6 = list3 ::: 1 :: 2 :: Nil
  val list7 = 0 :: list3 ::: 1 :: 2 :: Nil
  println(list3)
  println(list4)
  println(list5)
  println(list6)
  println(list7)

}
