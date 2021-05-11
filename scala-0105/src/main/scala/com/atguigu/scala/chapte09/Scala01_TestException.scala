package com.atguigu.scala.chapte09

/**
 * @author chujian
 * @create 2021-05-11 18:34
 *         异常处理
 *         java的异常执行原理
 *         程序运行的过程中，如果发生了异常，那么底层会创建对应的异常类型对象，通过throw关键字将异常向上抛出，
 *         jvm会找到能够对异常进行处理的代码（对异常进行捕获），将异常交给对应的处理代码进行处理
 *         异常处理的方法：通过throws声明异常，通过try..catch进行捕获处理
 *
 *         scala异常
 *         >他不区分编译时异常以及运行时异常
 *         >在进行异常捕获的时候，只有一个catch快，在catch块中，通过模式匹配，匹配不同类型异常
 *         而且在进行匹配的时候，就算是将大类型的异常放在前，也不会报错(不建议这么做)
 *         >通过@throws注解来标记方法可能会发生异常
 *
 */
object Scala01_TestException {
  def main(args: Array[String]): Unit = {

    try {
      //可能发生异常的代码
      var n = 10 / 0
    } catch {
      //异常处理的代码
      case e: ArithmeticException => {
        // 发生算术异常
        println("发生算术异常")
      }
      case e: Exception => {
        // 对异常处理
        println("发生了异常1")
        println("发生了异常2")
      }
    } finally {
      println("finally")
    }

    @throws(classOf[NumberFormatException])
    def f11() = {
      "abc".toInt
    }

    def main(args: Array[String]): Unit = {
      f11()
    }
  }
}
