package com.atguigu.scala.chapte10

/**
 * @author chujian
 * @create 2021-05-11 19:04
 *         隐式参数
 */
object Scala02_TestImplicit {
  implicit val str: String = "hello world!"

  def hello(implicit arg: String = "good bey world!"): Unit = {
    println(arg)
  }

  def main(args: Array[String]): Unit = {
    //隐式参数在调用的时候，直接通过方法名名称调用，不需要加括号
    hello
  }
}
