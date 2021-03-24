package com.hello.scala.funcCommon

object Hello16 extends App {

    val hello16_0 = new Hello16_0
    val hello16_1: Hello16_0 = new Hello16_1

    println(hello16_0.name)
    println(hello16_1.name)
}

class Hello16_0 {
  val name: String = "aaa"
}

class Hello16_1 extends Hello16_0 {
  override val name: String = "bbb"
}
