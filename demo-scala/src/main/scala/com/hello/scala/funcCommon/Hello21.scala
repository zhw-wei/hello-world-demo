package com.hello.scala.funcCommon

object Hello21 extends App {

  implicit def fun(d: Double): Int = {//定义隐式转换
    d.toInt
  }
  val num: Int = 2.0
  println(num)

  implicit def fun2(h: Hello21_0): Hello21_1={
    new Hello21_1
  }
  val hello = new Hello21_0
  hello.hello
  hello.world

}

class Hello21_0{
  def hello(): Unit ={
    println("hello")
  }
}

class Hello21_1{
  def world(): Unit ={
    println("world")
  }
}
