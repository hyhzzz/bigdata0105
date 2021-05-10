package com.atguigu.scala.chapte02

/**
 * @author chujian
 * @create 2021-05-09 17:26
 *         类型转换
 *         自动类型转换
 *         强转
 */
object Scala06_DataType {
  def main(args: Array[String]): Unit = {
    //（1）自动提升原则：有多种类型的数据混合运算时，系统首先自动将所有数据转换成精度大的那种数值类型，然后再进行计算
    //    var n = 1 + 2.0
    //    println(n)  // n 就是Double

    //（2）把精度大的数值类型赋值给精度小的数值类型时，就会报错，反之就会进行自动类型转换。
    //    var n2 : Double= 1.0
    //    var n3 : Int = n2 //错误，原因不能把高精度的数据直接赋值和低精度。

    //（3）（byte，short）和char之间不会相互自动转换。
    //    var n4 : Byte = 1
    //var c1 : Char = n4  //错误
    //    var n5:Int = n4

    //（4）byte，short，char他们三者可以计算，在计算时首先转换为int类型。
    //    var n6 : Byte = 1
    //    var c2 : Char = 1
    // var n : Short = n6 + c2 //当n6 + c2 结果类型就是int
    // var n7 : Short = 10 + 90 //错误


    //强制转换
    //（1）将数据由高精度转换为低精度，就需要使用到强制转换
    //    var n1: Int = 2.5.toInt // 这个存在精度损失

    //（2）强转符号只针对于最近的操作数有效，往往会使用小括号提升优先级
    //    var r1: Int = 10 * 3.5.toInt + 6 * 1.5.toInt  // 10 *3 + 6*1 = 36
    //    var r2: Int = (10 * 3.5 + 6 * 1.5).toInt  // 44.0.toInt = 44

    //    println("r1=" + r1 + " r2=" + r2)

    //数值类型和String类型间转换
    //（1）基本类型转String类型（语法：将基本类型的值+"" 即可）
    var str1: String = true + ""
    var str2: String = 4.5 + ""
    var str3: String = 100 + ""

    //（2）String类型转基本数值类型（语法：调用相关API）
    var s1: String = "12"

    var n1: Byte = s1.toByte
    var n2: Short = s1.toShort
    var n3: Int = s1.toInt
    var n4: Long = s1.toLong


  }
}
