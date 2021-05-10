package com.atguigu.scala.chapte06

import scala.beans.BeanProperty

/**
 * @author chujian
 * @create 2021-05-10 16:46
 *         类中的属性
 */
object Scala05_TestField {
  def main(args: Array[String]): Unit = {

    //创建对象
    val std: Student05 = new Student05

    //通过对象.方式访问属性
    std.name = "jingjing"
    println(std.name)

    println(std.sex)
  }
}

class Student05 {
  //在scala中，属性、方法、类默认修饰是public，但是public不能显式加  类似java中的default
  //底层生成的字节码文件对属性用private进行修饰，们提供公开的获取属性值以及设置属性值的方法
  var name: String = "chuijian"

  //如果想生成符合javabean规范的get set方法，需要在属性上加上@BeanProperty

  @BeanProperty
  var age: Int = 10

  //如果想给属性赋默认值的时候，需要声明的时候，赋_
  var sex: String = _  //null
}
