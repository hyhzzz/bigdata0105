package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-10 18:42
 *         构造方法的参数
 */
object Scala07_TestConstructor {
  def main(args: Array[String]): Unit = {

    //    val std: Student09 = new Student09
    val std = new Student09("chujian", 18)

    println(std.name)


  }


}

//只提供无参的主构造，单独定义
//class Student09 {
//  var name: String = _
//  var age: Int = _
//
//}

//在scala中用的少
//class Student09(namePararm: String, agePararm: Int) {
//  var name: String = namePararm
//  var age: Int = agePararm
//}

//在声明主构造方法参数的时候，如果参数前什么也不加，那么参数就作为当前类的局部变量使用,不能作为类的属性被访问
//如果需要将参数作为属性被访问的话，那么在参数声明前加var或者val关键字
class Student09(var name: String, var age: Int) {

  def m1(): Unit = {
    println(name)
    println(age)
  }
}


