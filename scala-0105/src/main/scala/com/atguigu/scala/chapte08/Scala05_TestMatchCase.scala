package com.atguigu.scala.chapte08

/**
 * @author chujian
 * @create 2021-05-11 13:07
 *         模式匹配
 */
object Scala05_TestMatchCase {
  def main(args: Array[String]): Unit = {
    var a: Int = 10
    var b: Int = 20
    var operator: Char = '+'
    //模式匹配是有返回值的
    //模式匹配中，沒有break关键字，case分支执行结束之后，直接跳出
    var result = operator match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      //所有case都不匹配 相当于java的default
      case _ => "运算符不合法"
    }
    println(result)
  }
}
