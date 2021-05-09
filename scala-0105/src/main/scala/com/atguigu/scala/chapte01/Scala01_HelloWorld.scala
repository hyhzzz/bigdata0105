package com.atguigu.scala.chapte01

/**
 * @author chujian
 * @create 2021-05-09 11:13
 *         这是第一次scala程序
 *
 *
 *         -object 名称
 *         从语法角度讲，上面语法表示声明了一个伴生对象
 *         Scala是纯面向对象的，去除了java中的static关键字，通过伴生对象模拟static效果
 *
 *         -伴生对象
 *         伴随类产生的一个对象
 *         当我们对源文件进行编译时，默认会生成两个字节码文件，一个是伴生类，另外一个是伴生对象所属类
 *         其实真正的伴生对象是伴生对象所属类中所创建的单例对象
 *
 *         如果不想默认生成伴生类，可以手动生成，要求伴生类名称和伴生对象名称一致
 *
 *
 *         注意：以后在scala语言中，如果要定义类似java的static内容， 都应该放到伴生对象中声明
 *
 *
 */
object Scala01_HelloWorld {
  def main(args: Array[String]): Unit = {
    println("HelloScala");
  }
}

//伴生对象
object Student {
  var bzr: String = "xulaoshi"

}

//伴生类
class Student {

  var name: String = _
  var age: Int = _
}
