package com.hello.scala.funcCommon

object Hello22 extends App {
  implicit class DB_22(mysql: Mysql_22){
    def select(): Unit ={
      println("select, hello")
    }
  }

  val mysql = new Mysql_22
  mysql.insert()
  mysql.select()
}

class Mysql_22{
  def insert(): Unit ={
    println("insert, hello")
  }
}


