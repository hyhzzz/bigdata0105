package com.atguigu.scala.chapte08

/**
 * @author chujian
 * @create 2021-05-11 18:15
 *         匹配对象及样例类
 */
class User(val name: String, val age: Int)

object User {
  //根据对象的属性创建对象
  def apply(name: String, age: Int): User = new User(name, age)

  //根据对象获取对象属性
  def unapply(user: User): Option[(String, Int)] = {
    if (user == null)
      None
    else
      Some(user.name, user.age)
  }
}

object Scala08_TestMatch {
  def main(args: Array[String]): Unit = {
    //创建对象
    val user: User = User("zhangsan", 11)
    //对对象进行模式匹配
    val result = user match {
      case User("zhangsan", 11) => "yes"
      case _ => "no"
    }
    println(result)
  }
}
