package com.atguigu.scala.chapte05

/**
 * @author chujian
 * @create 2021-05-10 7:59
 *
 *         函数和方法
 *         函数：将完成某个特殊的功能的代码块组合在一起  定义在方法或者函数内部  函数不能重写和重载
 *         方法：定义在类的下的函数，叫方法，方法支持重写和重载
 *
 *
 */
object Scala01_TestFun {

  //定义一个方法
  //  def sayHi(name: String): String = {
  //    return "hello-->" + name
  //  }

  def main(args: Array[String]): Unit = {

    //定义一个函数
    def sayHi(name: String): String = {
      return "hello-->" + name
    }


    //调用函数
    val str: String = sayHi("chujian")
    println(str)

  }
}
