package com.hello.scala.common

object Hello01 {

  def main(args: Array[String]): Unit = {
    println("hello world")

    val str0 = "hello"
    val str1 = " world!"

    println(str0 + str1)
    //注意：这里有一个小s为标识
    println(s"hello $str0, world $str1")

    val num0 = 1.5
    val num1: Float = 1.5f
    println(num0.getClass.getSimpleName)
    println(num1.getClass.getSimpleName)

    val num2 = if (num0 > num1) num0 else num1
    println(num2.getClass.getSimpleName)
    println(num2)

    val ++ = 10
    println(++)
  }
}

class Hello {
  var num2: Int = _ //下划线设置默认值，改语法只适用于类对象，不适用于局部变量
}
