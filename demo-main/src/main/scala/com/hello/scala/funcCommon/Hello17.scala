package com.hello.scala.funcCommon

object Hello17 extends App {

  val name = 10.0
  println(name.isInstanceOf[Long])

  val age = name.asInstanceOf[Int]
  println(age.asInstanceOf[Int])
}
