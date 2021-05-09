package com.atguigu.scala.chapte02

/**
 * @author chujian
 * @create 2021-05-09 15:14
 *         变量和常量
 *         变量：在程序执行的过程中，其值可以改变
 *         常量：在程序执行的过程中，其值不可以改变
 *
 *         java常量：final  数据类型 变量名 = 值
 *         java变量：数据类型 变量名 = 值
 *                  int a  =  10
 *
 *         Scala常量：val 变量名:数据类型  = 值
 *                   val a:Int = 10
 *         Scala变量：var 变量名:数据类型  = 值
 *                   var a:Int = 10
 *
 *
 *          开发中var和val优先选谁?
 *           val
 *
 */
object Scala02_Var {
  def main(args: Array[String]): Unit = {
//    （1）声明变量时，类型可以省略，编译器自动推导，即类型推导
        //声明一个整数类型变量 a 并将10赋值给a
//        var a= 10
//        var a:Int = 10
//        println(a)

//    （2）类型确定后，就不能修改，说明Scala是强数据类型语言。
//          var a = 10
//           a = "abc"

//    （3）变量声明时，必须要有初始值
//            var a:Int
//            println(a)

//    （4）在声明/定义一个变量时，可以使用var或者val来修饰，var修饰的变量可改变，val修饰的变量不可改。
//           var std1  = new Student()
//            std1 = new Student()

//            val std2  = new Student()
//            std2 = new Student()
//            std2.name = "list"

  }
}
//class Student{
//  val name:String = "zhangsan"
//}
