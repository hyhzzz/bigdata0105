package com.atguigu.scala.chapte05

/**
 * @author chujian
 * @create 2021-05-10 16:08
 *         惰性加载
 */
object Scala08_TestLazy {
  def main(args: Array[String]): Unit = {
    lazy val res = sum(10, 30)
    println("1.----------------")
    println("2.res=" + res)
  }

  def sum(a: Int, b: Int): Int = {
    println("3.sum被执行。。。")
     a+b
  }
}
