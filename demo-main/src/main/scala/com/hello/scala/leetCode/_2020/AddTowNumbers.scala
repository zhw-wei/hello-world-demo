package com.hello.scala.leetCode._2020

object AddTowNumbers extends App {

  val l1 = new ListNode(2)
  val l1_1 = new ListNode(4)
  val l1_2 = new ListNode(3)
  val l1_3 = new ListNode(3)
  l1.next = l1_1
  l1_1.next = l1_2
  l1_2.next = l1_3

  val l2 = new ListNode(5)
  val l2_1 = new ListNode(6)
  val l2_2 = new ListNode(4)
  l2.next = l2_1
  l2_1.next = l2_2

  //  println(l1)
  //  println(l2)

  println(addTwoNumbers(l1, l2))
  println("==========")
  val node = new ListNode(5)
  node.next = new ListNode(5)
  val node2 = new ListNode(5)
  //  println(node)
  //  println(node2)
  println(addTwoNumbers(node, node2))

  /**
   * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
   * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
   * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
   *
   * 示例：
   * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
   * 输出：7 -> 0 -> 8
   * 原因：342 + 465 = 807
   *
   */
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {

    /*
    f(1) = 1
    f(2) = f(1) + 1
    f(3) = f(2) + 1
    使用了递归，效率有点低
     */
    def nodeSize(node: ListNode): Int = if (node.next == null) 1 else nodeSize(node.next) + 1

    var node1 = l1
    var node2 = l2

    val result = new ListNode(-1)
    var current = result
    var last = current
    var n = 0

    val maxSize = Math.max(nodeSize(l1), nodeSize(l2))
    for (i <- 0 to maxSize) {
      if (node1.next == null) node1.next = new ListNode(0)
      if (node2.next == null) node2.next = new ListNode(0)

      var total = node1.x + node2.x + n
      n = 0
      if (total > 9) {
        total -= 10
        n = 1
      }
      current.next = new ListNode(total)
      last = current

      node1 = node1.next
      node2 = node2.next
      current = current.next
    }

    if (last.next.next == null && last.next.x == 0) last.next = null

    result.next
  }

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x

    override def toString: String = {
      super.toString
      s"x = $x, next = ($next)"
    }
  }

}
