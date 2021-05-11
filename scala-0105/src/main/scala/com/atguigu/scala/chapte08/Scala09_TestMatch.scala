package com.atguigu.scala.chapte08

/**
 * @author chujian
 * @create 2021-05-11 18:17
 *         样例类
 */
case class Student(var name: String, var age: Int) {}

object Scala09_TestMatch {
  def main(args: Array[String]): Unit = {
    val std1: Student = Student("chujian", 18)
    val res: String = std1 match {
      case Student("chujian", 18) => "success"
      case _ => "fail"
    }
    println(res)
  }
}
