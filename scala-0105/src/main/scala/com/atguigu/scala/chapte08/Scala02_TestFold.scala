package com.atguigu.scala.chapte08

import scala.collection.mutable

/**
 * @author chujian
 * @create 2021-05-11 12:13
 * Fold折叠：化简的一种特殊情况。
 */
object Scala02_TestFold {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)

    // fold方法使用了函数柯里化，存在两个参数列表
    // 第一个参数列表为 ： 零值（初始值）
    // 第二个参数列表为： 简化规则

    // fold底层其实为foldLeft
    val i = list.foldLeft(1)((x, y) => x - y)
    // val j = list.foldLeft(10)(_-_)

    val i1 = list.foldRight(10)((x, y) => x - y)
    // val j = list.foldRight(10)(_-_)
    println(i)
    println(i1)



  }

}
