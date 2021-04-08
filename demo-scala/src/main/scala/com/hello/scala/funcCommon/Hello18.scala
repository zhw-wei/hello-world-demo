package com.hello.scala.funcCommon

object Hello18 extends App {

  println(Hello18_0.name)
  Hello18_0.hello

  val hello18_0 = Hello18_0()//不可以使用new创建对象，因为没有无参构造方法，apply函数有无参的
  println("==============")
  val hello18_1 = new Hello18_0(30) //调用构造方法
  println("==============")
  val hello18_2 = Hello18_0(20)//调用apply(age)方法

  println(s"aaa = ${hello18_0.age}, bbb = ${hello18_1.age}, ccc = ${hello18_2.age}")

  println("================")
  /**
   * apply方法是一种语法糖，而不是构造方法
   * 此处等同于<code>Hello18_0.apply("aab")</code>
   */
  val str = Hello18_0("aab")
  println(s"string = $str")
}

class Hello18_0(inAge: Int){
  val age = inAge
}
object Hello18_0{
  val name = "aaa"
  def hello = println("hello")

  def apply(): Hello18_0 = new Hello18_0(10)
  def apply(age: Int): Hello18_0 = {
    println("hello apply")
    new Hello18_0(age)
  }

  def apply(str: String): String = {
    str
  }
}
