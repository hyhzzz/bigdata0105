package com.atguigu.scala.chapte08

/**
 * @author chujian
 * @create 2021-05-11 13:10
 *         模式守卫
 */
object Scala06_TestMatchGuard {
  def main(args: Array[String]): Unit = {
    //通过模式守卫，求一个整数的绝对值
    def abs(x: Int) = x match {
      case i: Int if i >= 0 => i //在处理之前，对当前i变量进行判断
      case j: Int if j < 0 => -j
      case _ => "type illegal"
    }

    println(abs(-5))

  }

}
