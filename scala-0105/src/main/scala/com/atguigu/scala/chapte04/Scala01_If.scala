package com.atguigu.scala.chapte04

import scala.io.StdIn

/**
 * @author chujian
 * @create 2021-05-09 21:43
 */
object Scala01_If {
  def main(args: Array[String]): Unit = {

    //单分支
    //    println("请输入年龄")
    //    val age: Int = StdIn.readInt()
    //    if (age < 18) {
    //      println("童年")
    //    }

    //双分支
    //    println("input age:")
    //    var age = StdIn.readShort()
    //
    //    if (age < 18) {
    //      println("童年")
    //    } else {
    //      println("成年")
    //    }

    //多分支
    //    println("input age")
    //    var age = StdIn.readInt()
    //
    //    if (age < 18) {
    //      println("童年")
    //    } else if (age >= 18 && age < 30) {
    //      println("中年")
    //    } else {
    //      println("老年")
    //    }

    //分支的发返回值
    //    println("input age")
    //    var age = StdIn.readInt()
    //
    //    val res: String = if (age < 18) {
    //      "童年"
    //    } else if (age >= 18 && age < 30) {
    //      "中年"
    //    } else {
    //      "老年"
    //    }
    //    //() 其实就是unit
    //    println(res)

    //Scala中返回值类型不一致，取它们共同的祖先类型
    //    val age = 30
    //    val result: Any = if (age < 18) {
    //      "童年"
    //    } else if (age <= 30) {
    //      "青年"
    //    } else if (age <= 50) {
    //      "中年"
    //    } else {
    //      100
    //    }
    //    println(result)


    //Java中的三元运算符可以用if else实现
    println("input age")
    var age = StdIn.readInt()
    val res: Any = if (age < 18) "童年" else "成年"
    "不起作用"
    println(res)
  }
}
