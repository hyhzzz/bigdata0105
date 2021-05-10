package com.atguigu.scala.chapte05

/**
 * @author chujian
 * @create 2021-05-10 11:53
 *         高阶函数之函数作为返回值
 */
object Scala06_TestFun_Return {
  def main(args: Array[String]): Unit = {
    def f1(): (Int) => Int = {
      var a: Int = 10

      def f2(b: Int): Int = {
        a + b
      }
      f2 _
    }

    //执行f1函数,将返回的f2赋值给ff变量
    val ff: Int => Int = f1()

    //闭包：内存函数f2要访问外层函数f1聚合变量a,当外层函数f1执行结束之后，f1会释放栈内存，但是会自动延迟f1函数的局部变量的生命周期
    //和内存函数f2形成一个闭合的效果。
    //调用ff函数其实就是调用f2
    println(ff(20))

    //也可以这样调用
    println(f1()(20))

  }
}
