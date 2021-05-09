package com.atguigu.scala.chapte02

/**
 * @author chujian
 * @create 2021-05-09 16:45
 *         数据类型
 */
object Scala05_DataType {
  def main(args: Array[String]): Unit = {


    //    var b:Byte = 10 + 20
    //    println(b)

    //（1）字符常量是用单引号 ' ' 括起来的单个字符。
    //    var c1: Char = 'a'
    //    println("c1=" + c1)
    //
    //    //注意：这里涉及自动类型提升，其实编译器可以自定判断是否超出范围，
    //    //不过idea提示报错
    //    var c2:Char = 'a' + 1
    //    println(c2)
    //
    //    //（2）\t ：一个制表位，实现对齐的功能
    //    println("姓名\t年龄")
    //
    //    //（3）\n ：换行符
    //    println("西门庆\n潘金莲")
    //
    //    //（4）\\ ：表示\
    //    println("c:\\岛国\\avi")
    //
    //    //（5）\" ：表示"
    //    println("同学们都说：\"大海哥最帅\"")

    //    println(m1())
//    println(m2(1))
  }

  def m1(): Unit = {
    println("m1方法被执行了")
  }

  def m2(n: Int): Nothing = {
    if (n == 0) {
     throw  new NullPointerException
    } else {
      //在scala语言中，nothing类型表示发生异常了
      throw new RuntimeException
    }
  }
}
