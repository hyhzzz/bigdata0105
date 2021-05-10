package com.atguigu.scala.chapte04

/**
 * @author chujian
 * @create 2021-05-09 23:03
 *         for循环
 */
object Scala02_For {
  def main(args: Array[String]): Unit = {

    //对范围数据进行遍历  to
    //    for (i <- Range(1, 5)) { // 范围集合
    //      println("i = " + i)
    //    }
    //在声明变量的时候，类型可以省略，因为编译器可以根据集合中的数据，推导出变量类型
    //    for (i <- 1 to 5) { // 包含5
    //      println("i = " + i)
    //    }
    //    for (i <- 1 until 5) { // 不包含5
    //      println("i = " + i)
    //    }

    //    println("===")

    //循环守卫
    //在scala中，没有contine和break关键字
    //通过if判断模拟contiune跳出本次循环
    //通过调用方法模拟break跳出整个循环
    //        for (i <- 1 to 5 if i != 3) {
    //          println(i)
    //        }

    //    println("===")
    //
    //    //循环步长
    //    for (i <- 1 to 10 by 2) {
    //      println("i=" + i)
    //    }

    //嵌套循环
    //    for(i <- 1 to 3; j <- 1 to 3) {
    //      println(" i =" + i + " j = " + j)
    //    }

    //    for (i <- 1 to 3; j = 4 - i) {
    //      println("i=" + i + " j=" + j)
    //    }


    //倒叙打印
    //    for (i <- 1 to 10 reverse) {
    //      println(i)
    //    }

    //九九乘法表
    for (i <- 1 to 9) {
      for (j <- 1 to i) {
        print(j + "*" + i + "=" + (i * j) + "\t")
      }
      println()
    }
  }
}
