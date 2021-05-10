package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 16:22
 *         对象和类
 *         -在自然界中，只要是客观存在的都是对象(万物皆对象)
 *         -对大量对象共性进行抽象
 *         >有什么   属性
 *         >能做什么  方法
 *         >属性和方法虽然定义在类中，但是属于每一个对象
 *         -在java|scala语言中，类是创建对象的模板
 */
object Scala01_TestObject {
  def main(args: Array[String]): Unit = {

    //创建对象
    val std = new Student01
    println(std.name)
    std.study()

  }
}

//定义一个类
class Student01 {
  var name: String = "chujian"

  def study(): Unit = {
    println(s"${name}在学习")
  }
}
