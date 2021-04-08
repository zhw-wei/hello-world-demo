package com.hello.scala.funcSenior

import scala.collection.parallel.CollectionConverters._

object Hello56 extends App {

  val t = (1 to 10000).map(_ => Thread.currentThread().getName).distinct
  val t1 = (1 to 10000).par.map(_ => Thread.currentThread().getName).distinct

  for (v <- t){ //只有main一个线程
    println(v)
  }
  println("============")

  for(v <- t1){ //和运行计算机的内核数相关
    println(v)
  }

}
