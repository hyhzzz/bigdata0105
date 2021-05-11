package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 21:35
 *         创建对象的方式
 *         java
 *         new
 *         反射
 *         工厂
 *         反序列化
 *
 *         scala
 *         new
 *         底层调用类的构造方法
 *         apply
 */
object Scala12_TestCreateObject {
  def main(args: Array[String]): Unit = {
    //通过new方式创建对象，底层调用的是构造方法
    //    val std1 = new Student15()
    //    println(std1)
    //    val std2: Student15 = new Student15("chujian", 18)
    //    println(std2)


    val std: Student15 = Student15("chujian", 18)
    println(std)
  }
}

object Student15 {
  //通过apply方法创建对象 无参
  //  def apply(): Student15 = new Student15()
  //有参  可以重载
  def apply(name: String, age: Int): Student15 = new Student15(name, age)

}

class Student15 {
  var name: String = _
  var age: Int = _

  def this(name: String, age: Int) {
    this()
    this.name = name
    this.age = age
  }

  override def toString = s"Student15($name, $age)"
}
