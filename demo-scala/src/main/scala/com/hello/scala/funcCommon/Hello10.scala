package com.hello.scala.funcCommon

object Hello10 {
  def main(args: Array[String]): Unit = {
    println(feb(8))
    println(feb(9))
    println(feb(10))
    println(test())
    println(test(20))
    println(test2(10, 20, 30))

    lazy val res0 = feb(20);

    val a = 10
    val b = 0
    try {
      println(a / b)
    } catch {
      case ex: ArithmeticException => println("ArithmeticException, " + ex.getMessage)
      case ex: Exception => println("Exception, " + ex.getMessage)
    } finally {
      println("finally")
    }
    test3()
  }

  def test(a: Int = 10): Int = {
    a
  }

  def test2(a: Int, b: Int*): Int = {
    a + b.sum
  }

  def test3(): Nothing={
    throw new NullPointerException("hello null pointer exception")
  }

  /**
   * 斐波那契额数列
   * f(1) = 1
   * f(2) = 1
   * f(n) = f(n-1) + f(n-2)
   */
  def feb(index: Int): Int = {
    if (index <= 2) 1 else feb(index - 1) + feb(index - 2)
  }
}
