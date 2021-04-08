package com.hello.scala.funcCommon

object Hello12 {
  def main(args: Array[String]): Unit = {
    val hello1 = new Hello("test")
    println(hello1)

    val hello2 = new Hello("test", 10)
    println(hello2)

    test
    test01

    println(test02(1)(2))
    println(test03(1))
    println(test03(2)(1))
    println(test03(1)(1))

  }

  def test: Unit = {
    println("test01")
  }

  def test01 = println("test02")

  //柯里化
  def test02(a: Int)(b: Int) = a * b

  def test03(a: Int) = {
    if (a > 1) (b: Int) => a - b else (b: Int) => a + b
  }
}
