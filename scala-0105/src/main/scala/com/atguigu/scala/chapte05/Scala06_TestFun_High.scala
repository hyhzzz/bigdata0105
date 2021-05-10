package com.atguigu.scala.chapte05

/**
 * @author chujian
 * @create 2021-05-10 10:00
 *         高阶函数
 */
object Scala06_TestFun_High {
  def main(args: Array[String]): Unit = {
    //函数正常声明
    //    def foo(): Int = {
    //      println("foo...")
    //      10
    //    }

    //    foo()
    //将foo函数的执行结果赋值给res变量
    //    val res: Int = foo()
    //    println(res)

    //函数作为值进行传递 语法：在函数名称的后面 + 空格 加下划线
    //注意：将foo函数作为一个整体，赋值给f变量，f是函数类型()=>Int
    //    val f: () => Int = foo _
    //这个时候，f就是一个函数，如果想要运行f函数，必须加()
    //    println(f())

    //函数可以作为参数进行传递，基本上都是通过匿名函数
    //    def calculator(a: Int, b: Int, op: (Int, Int) => Int): Int = {
    //      op(a, b)
    //    }

    //定义一个函数，f1，完成两个整数加法运算
    //    def op(a: Int, b: Int): Int = {
    //      a + b
    //    }

    //    println(calculator(10, 20, op))

    //    println(calculator(50, 20, (a: Int, b: Int) => {
    //      a - b
    //    }))

    //    println(calculator(50, 20, (a: Int, b: Int) => a - b))
    //    println(calculator(50, 20, (a, b) => a - b))
    //    println(calculator(50, 20,_-_))


    //函数可以作为函数返回值返回，使用的是嵌套函数
    def f1(): () => Unit = {
      def f2(): Unit = {
        println("f2函数被调用了。。")
      }
      //将f2作为返回值，返回给f1
      f2 _

    }

    //ff就是f2
//    val ff: () => Unit = f1()
//    ff()

    f1()()
  }
}
