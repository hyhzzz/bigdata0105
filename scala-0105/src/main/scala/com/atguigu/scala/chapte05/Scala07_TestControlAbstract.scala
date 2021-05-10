package com.atguigu.scala.chapte05

/**
 * @author chujian
 * @create 2021-05-10 15:34
 *         控制抽象
 *         值调用：把计算后的值传递过去
 *         名调用：把代码块传递过去
 */
object Scala07_TestControlAbstract {
  def main(args: Array[String]): Unit = {
    //    def foo(a: Int): Unit = {
    //      println(a)
    //      println(a)
    //    }
    //
    //    def f(): Int = {
    //      println("f...")
    //      10
    //    }
    //
    //    foo(f)


    //名调用
    def foo(a: => Int): Unit = {
      println(a)
      println(a)
    }

    def f(): Int = {
      println("f...")
      10
    }
    foo(f)
  }
}
