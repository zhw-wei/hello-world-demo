package com.hello.scala.funcSenior

import scala.collection.{immutable, mutable}

object Hello53 extends App {
  val queue = mutable.Queue[Int]()//可修改
  queue.enqueue(1 ,2, 3, 4, 5)
  println(queue)

  val queue1 = immutable.Queue[Int](-2, -1, 0)//不可修改
  queue1.enqueue(1, 2, 3, 4, 5)//无法添加
  println(queue1)

  println(queue.head)
  println(queue.tail)
  println(queue.tail.tail)
  println("==================")

  var queue_2 = queue
  while(!queue_2.isEmpty){
    queue_2 = queue_2.tail
    println(queue_2)
  }

  println(queue.dequeue())
  println(queue)
}
