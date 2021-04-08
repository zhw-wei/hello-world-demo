package com.hello.scala.funcCommon

object Hello20 extends App {

  val mysql = new Mysql with Operator
  mysql.select(10)
  mysql.insert(20)

  val mysql2 = new Mysql2 with Operator {
    override def delete: Unit = println("delete")
  }

  mysql2.delete
  mysql2.insert(40)

}

trait Operator{
  def insert(id: Int): Unit ={
    println(s"insert, $id")
  }
}

class Mysql{
  def select(id: Int): Unit ={
    println(s"select, $id")
  }
}

abstract class Mysql2{
  def delete
}
