package com.hello.scala.funcCommon

object Hello11 {
  def main(args: Array[String]): Unit = {
    val person = new Person
    println(person)

    person.name = "hello"
    person.age = 10
    println(person)

    val list = List("aa", "bb", "cc")
    println(list)
    println(list.reverse)

  }
}

class Person{
  var name: String = _
  var age: Int = _

  override def toString: String = {
    s"name: $name, age: $age"
  }
}
