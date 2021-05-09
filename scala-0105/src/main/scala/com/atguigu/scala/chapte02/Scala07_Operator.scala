package com.atguigu.scala.chapte02

/**
 * @author chujian
 * @create 2021-05-09 19:30
 *         运算符
 */
object Scala07_Operator {
  def main(args: Array[String]): Unit = {

    //算数运算符
    //    var r1: Int = 10 / 3 //3
    //    println(r1)
    //
    //    var r2: Double = 10 / 3 //3.9
    //    println(r2)

    //关系运算符  // 测试：>、>=、<=、<、==、!=
    /**
     *
     * java
     * ==比较的是是对象的内存地址
     * equals 默认和==一样，也是比较的地址，string对equals方法进行了重写，字符串的equals比较的是内容
     *
     * scala
     * ==和equals功能一样，比较的是内容是否相同
     * 判断地址是否相同要使用eq
     */
    //    var a: Int = 2
    //    var b: Int = 1

    //    println(a > b) // true
    //    println(a >= b) // true
    //    println(a <= b) // false
    //    println(a < b) // false
    //    println("a==b" + (a == b)) // false
    //    println(a != b) // true

    //逻辑运算符
    // 测试：&&、||、!
    //    var a = true
    //    var b = false
    //
    //    println("a&&b=" + (a && b))     // a&&b=false
    //    println("a||b=" + (a || b))     // a||b=true
    //    println("!(a&&b)=" + (!(a && b))) // !(a&&b)=true

    //赋值运算符
    var r1 = 10
    //在java语言中，+=运算符可以自动进行强转，在scala中+=底层不会自动进行强转
    r1 += 1 // 没有++
    r1 -= 2 // 没有--


  }
}
