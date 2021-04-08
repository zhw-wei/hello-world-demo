package com.hello.scala.funcCommon

import scala.beans.BeanProperty

object Hello13 {

  def main(args: Array[String]): Unit = {

    val test = new Test("test")
    println(s"test.name = ${test.name}")

    val test01 = new Test01("test01")
    test01.inName = "test01_inName"
    println(s"test01.inName = ${test01.inName}, test01.name = ${test01.name}")

    val test02 = new Test02("test02")
    println(s"test02.inName = ${test02.inName}, test02.name = ${test02.name}")

    val test03 = new Test03("test03")
    println(s"test03.name = ${test03.name}, test03.getName = ${test03.getName}")

  }
}

class Test(inName: String){
  var name: String = inName
}

class Test01(var inName: String){
  var name: String = inName
}

class Test02(val inName: String){
  var name: String = inName
}

class Test03(inName: String){
  @BeanProperty var name: String = inName
}
