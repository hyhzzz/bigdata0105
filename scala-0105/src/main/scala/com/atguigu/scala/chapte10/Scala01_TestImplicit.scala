package com.atguigu.scala.chapte10

/**
 * @author chujian
 * @create 2021-05-11 18:52
 *         隐试函数
 *         可以动态的扩展类的功能
 *         当编译器第一次编译失败的时候，他会在当前环境中查找能让编译通过的方法，能用于
 *         将类型进行转换，转换之后，进行二次编译，实现对类功能的扩展
 *
 */
object Scala01_TestImplicit {

  //将int类型数据，转换为JRichInt
  implicit def convert(a: Int): JRichInt = {
    new JRichInt(a)
  }


  def main(args: Array[String]): Unit = {
    println(2.jMax(5))
  }
}

class JRichInt(var self: Int) {
  def jMax(i: Int): Int = {
    if (self < i) i else self

  }

  def jMin(i: Int): Int = {
    if (self < i) i else i

  }
}