package com.hello.scala.funcCommon

object Hello15 {

  def main(args: Array[String]): Unit = {
    val hello15_1 = new Hello15_1("name", 10)
    println(hello15_1.name)
    println(hello15_1.age)
  }
}

class Hello15_0(inName: String){
  var name = inName
}

class Hello15_1(inName: String, inAge: Int) extends Hello15_0(inName){
  var age = inAge
}
