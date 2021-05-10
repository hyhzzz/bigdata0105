package com.atguigu.scala.chapte05

/**
 * @author chujian
 * @create 2021-05-10 8:30
 *         函数的至简原则：能省则省
 */
object Scala04_TestFun {
  def main(args: Array[String]): Unit = {
    //    // （0）函数标准写法
    //    def f(s: String): String = {
    //      return s + " jinlian"
    //    }
    //
    //    println(f("Hello"))
    //
    //    // 至简原则:能省则省
    //    //（1） return可以省略,Scala会使用函数体的最后一行代码作为返回值
    //    def f1(s: String): String = {
    //      s + " jinlian"
    //    }
    //
    //    println(f1("Hello"))
    //
    //    //（2）如果函数体只有一行代码，可以省略花括号
    //    def f2(s: String): String = s + " jinlian"
    //
    //    //（3）返回值类型如果能够推断出来，那么可以省略（:和返回值类型一起省略）
    //    def f3(s: String) = s + " jinlian"
    //
    //    println(f3("Hello3"))
    //
    //    //（4）如果有return，则不能省略返回值类型，必须指定。
    //    def f4(): String = {
    //      return "ximenqing4"
    //    }
    //
    //    println(f4())
    //
    //    //（5）如果函数明确声明unit，那么即使函数体中使用return关键字也不起作用
    //    def f5(): Unit = {
    //      return "dalang5"
    //    }
    //
    //    println(f5())
    //
    //    //（6）Scala如果期望是无返回值类型,可以省略等号
    //    // 只有参数，没有返回，一般只是用于调用逻辑代码，不需要返回任何结果，将无返回值的函数称之为过程
    //    def f6() {
    //      "dalang6"
    //    }
    //
    //    println(f6())
    //
    //    //（7）如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
    //    def f7() = "dalang7"
    //
    //    println(f7())
    //    println(f7)
    //
    //    //（8）如果函数没有参数列表，那么小括号可以省略,调用时小括号必须省略
    //    def f8 = "dalang"
    //    //println(f8())
    //    println(f8)

    //（9）如果不关心名称，只关心逻辑处理，那么函数名（def）可以省略
//    def f9(f: (String) => Unit): Unit {
//        f("atguigu")
//    }

    //    def f10(f: String => Unit) = {
    //      f("")
    //    }
    //
    //    f10(f9)
    //    println(f10((x: String) => {
    //      println("wusong")
    //    }))


//    f9((s: String
    //
    //       ) => {
    //      println(s)
    //    }
    //    )


    // （1）定义一个函数：参数包含数据和逻辑函数
    def operation(arr: Array[Int], op: Int => Int) = {
      for (elem <- arr) yield op(elem)
    }

    // （2）定义逻辑函数
    def op(ele: Int): Int = {
      ele + 1
    }

    // （3）标准函数调用
    val arr = operation(Array(1, 2, 3, 4), op)
    println(arr.mkString(","))

    // （4）采用匿名函数
    val arr1 = operation(Array(1, 2, 3, 4), (ele: Int) => {
      ele + 1
    })
    println(arr1.mkString(","))

    // （4.1）参数的类型可以省略，会根据形参进行自动的推导;
    val arr2 = operation(Array(1, 2, 3, 4), (ele) => {
      ele + 1
    })
    println(arr2.mkString(","))

    // （4.2）类型省略之后，发现只有一个参数，则圆括号可以省略；其他情况：没有参数和参数超过1的永远不能省略圆括号。
    val arr3 = operation(Array(1, 2, 3, 4), ele => {
      ele + 1
    })
    println(arr3.mkString(","))

    // (4.3) 匿名函数如果只有一行，则大括号也可以省略
    val arr4 = operation(Array(1, 2, 3, 4), ele => ele + 1)
    println(arr4.mkString(","))

    //（4.4）如果参数只出现一次，则参数省略且后面参数可以用_代替
    val arr5 = operation(Array(1, 2, 3, 4), _ + 1)
    println(arr5.mkString(","))

  }

}
