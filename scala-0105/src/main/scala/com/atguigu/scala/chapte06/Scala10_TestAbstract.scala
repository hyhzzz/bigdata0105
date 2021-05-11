package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 20:11
 *         抽象
 */
object Scala10_TestAbstract {

}

abstract class Person12 {

  //非抽象属性
  var name: String = "zhangsan"

  //抽象属性
  var age: Int

  //非抽象方法
  def eat(): Unit = {
    println("Person s eat ")
  }

  //抽象方法
  def sleep(): Unit
}

class Student12 extends Person12 {
  //重写除了方法之外，还要属性的重写
  //在子类中，如果重写的是抽象属性和方法，那么override关键字可以省略
  var age: Int = 18

  def sleep(): Unit = {
    println("bangzhang sleep")
  }

  //对父类的非抽象方法进行重写
  //override关键字不能省略，必须得加
  override def eat(): Unit = {
    //可以通过super关键字调用父类的方法
    super.eat()
    println("student s eat")
  }

}
