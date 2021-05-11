package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 23:07
 *         特质声明以及混入特质
 */
object Scala13_TestTrait {
  def main(args: Array[String]): Unit = {

    var a: PersonTraintl17 = new MyClass17
    println(a.name)
      a.eat()
  }
}

//特质声明
trait PersonTraintl17 {

  //抽象属性
  var name: String

  //抽象方法
  def eat(): Unit

  //非抽象属性
  var age: Int = 10

  //非抽象方法
  def sleep(): Unit = {
    println("Person s sleep")
  }
}

//混入特质
class MyClass17 extends PersonTraintl17 {
  override var name: String = "chujian"

  override def eat(): Unit = {
    println("mc eat")
  }
}
