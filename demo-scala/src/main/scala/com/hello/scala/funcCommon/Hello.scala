package com.hello.scala.funcCommon

class Hello private{ //主构造方法私有，禁止外部调用
  var name: String = _
  var age: Int = _

  println("init class hello")

  def this(inName: String){
    this
    name = inName
  }

  def this(inName: String, inAge: Int){
    this(inName)
    age = inAge
  }

  override def toString: String = {
    s"name: $name, age: $age"
  }
}
