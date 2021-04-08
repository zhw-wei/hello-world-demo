package com.hello.scala.funcSenior

object Hello58 extends App {

  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  //使用view
  //filter中的函数已经执行了
  val list2 = list.view.filter({
    println("hello")
    _ % 2 == 0
  })
  println(list2)  //不可以取出数据

  for(i <- list2){
    println(i)  //可以取出数据
  }
  println(list2.head)//可以取出数据
  println(list2.tail)//不可以取出数据
  println(list2)    //不可以取出数据
  /**
   * 总结：只有在取出集合中的单条数据时才能拿到数据，否则不能拿到数据
   */
}
