package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 19:28
 *         继承关系，子类对象的创建过程
 *
 */
object Scala08_TestExtends {
  def main(args: Array[String]): Unit = {
    val std: Student10 = new Student10()

  }

}

class Person10 {
  println("1-父类的主构造方法被调用了")
  var name: String = _
  var age: Int = _

  def this(name: String, age: Int) {
    this()
    println("2-父类的辅助构造方法被调用了")
    this.name = name
    this.age = age
  }
}

class Student10 extends Person10 {
  println("3-子类的主构造方法被调用了")
  var stdNo: String = _

  def this(name: String, age: Int, stdNo: String) {
    this()
    println("4-子类的辅助造方法被调用了")
  }
}
