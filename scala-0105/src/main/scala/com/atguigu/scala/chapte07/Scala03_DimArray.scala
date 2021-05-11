package com.atguigu.scala.chapte07

/**
 * @author chujian
 * @create 2021-05-11 10:19
 *         多维数组
 *
 */
object Scala03_DimArray {
  def main(args: Array[String]): Unit = {

    //（1）创建了一个二维数组, 有三个元素，每个元素是，含有4个元素一维数组()
    val arr = Array.ofDim[Int](3, 4)
    arr(1)(2) = 88

    //（2）遍历二维数组
    for (i <- arr) { //i 就是一维数组

      for (j <- i) {
        print(j + " ")
      }

      println()
    }
  }
}
