package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 18:00
 *         构造器（构造方法）
 *         构造对象
 *         初始化属性（给对象的属性赋值）
 *
 *         java中的构造器
 *         方法名和类名保持一致
 *         构造方法没有返回值类型
 *         构造方法可以重载
 *
 *
 *         scala中的构造器
 *         主构造器和辅助构造器
 *         主构造器：在类名后的构造方法称之为主构造器，主要完成功能完成类的初始化
 *         辅助构造器：为类完成初始化的辅助功能而提供的构造器
 *
 *
 *
 */
object Scala06_TestConstructor {
  def main(args: Array[String]): Unit = {

//    val std: Student08 = new Student08
    val std: Student08 = new Student08("chujian")

  }
}

class Student08 {
  println("Student08的主构造方法被调用咯")

  var name: String = _
  var age: Int = _


  //声明辅构造犯法
  def this(name: String) {
    //调用主构造方法
    this()
    println("Student08辅构造方法被调用了")
    this.name = name
  }

  //下面这个写法不是构造方法
  //  def Student08(): Unit = {
  //    println("Student08的构造方法被调用了")
  //  }
}
