package com.atguigu.scala.chapte02

/**
 * @author chujian
 * @create 2021-05-09 16:00
 *         字符串输出
 */
object Scala04_TestString {
  def main(args: Array[String]): Unit = {
    //1.字符串+连接
    var name: String = "jingjing"
    var age: Int = 18
    //    println(age+"岁的"+name+"在0105学习")


    //2.通过prinf
    // 传值字符串(格式化字符串)
    //    printf("%d岁的%s在0105学习", age,name)

    //通过插值字符串

    // 将变量值插入到字符串
    //    println(s"name=${name},age=${age}")


    //多行字符串

    val unit: Unit = println(
      s"""
        |name=${name}
        |
        |age=${age}
        |
        |
        |""".stripMargin)

    println(unit)
  }
}
