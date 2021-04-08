package com.hello.scala.funcSenior

object Hello55 extends App {

  val f = () => "hello world"

  val f2 = f
  val f3 = f _

  println(f2)
  println(f3)
  println("=====a======")
  println(f2.apply)
  println(f3.apply)
  println(f3.apply.apply)
  println("=====b======")

  def fun(): String = {
    "hello world"
  }

  val fun2 = fun
  val fun3 = fun _

  println(fun2)
  println(fun3)
  println("=====c======")
  println(fun3.apply)
}
