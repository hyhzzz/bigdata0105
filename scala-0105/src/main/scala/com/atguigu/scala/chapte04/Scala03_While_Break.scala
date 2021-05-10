package com.atguigu.scala.chapte04

import scala.util.control.Breaks.{break, breakable}

/**
 * @author chujian
 * @create 2021-05-10 0:29
 *         循环终端
 */
object Scala03_While_Break {
  def main(args: Array[String]): Unit = {

    //    var i = 1
    //    while (i <= 10) {
    //      println("初见" + i)
    //      i += 1
    //    }

    //循环中断
    //continue：终止当前正在执行的本次循环，继续下一次
    //break：终止整个循环
    //scala是完全面向对象的语言，所以无法使用break，continue关键字这样的方式来中断，或继续循环逻辑，而是采用了函数式编程的方式代替了循环语法中的break和continue

    //采用异常的方式退出循环
    //    try {
    //      for (i <- 1 to 10) {
    //        println(i)
    //        if (i == 5) throw new RuntimeException
    //      }
    //    } catch {
    //      case e =>
    //    }
    //    println("正常结束循环")


    //采用Scala自带的函数，退出循环
    breakable(
      for (elem <- 1 to 10) {
        println(elem)
        if (elem == 5) {
          //跳出整个循环
          break()
        }
      }
    )
    println("正常结束循环")


  }
}
