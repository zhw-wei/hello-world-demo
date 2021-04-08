package com.hello.scala.funcSenior

import scala.collection.mutable

object Hello54 extends App {
  val map = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c", 4 -> "d", 5 -> "e")
  println(map)
  for((k, v) <- map){
    println(s"key = $k, value = $v")
  }
  println(map.get(1))
  println(map.getOrElse(6, "f"))

  println("===========================")
  val map1 = mutable.Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c", 4 -> "d", 5 -> "e")
  println(map1)
  map1 += (6 -> "f")
  println(map1)

  map1 -= 1
  println(map1)

}
