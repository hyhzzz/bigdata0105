package com.atguigu.scala.chapte08

/**
 * @author chujian
 * @create 2021-05-11 16:55
 */
object Scala07_TestMatch {
  def main(args: Array[String]): Unit = {
    //匹配常量
    //    def describe(x: Any) = x match {
    //      case 5 => "Int five"
    //      case "hello" => "String hello"
    //      case true => "Boolean true"
    //      case '+' => "Char +"
    //    }
    //    println(describe(5))

    //匹配类型
    //    def describe(x: Any) = x match {
    //
    //      case i: Int => "Int"
    //      case s: String => "String hello"
    //      case m: List[_] => "List"
    //      case c: Array[Int] => "Array[Int]"
    //      如果以上case都没有匹配上的话，那么下面可以用下划线表示默认情况，但是通过下划线没有办法获取到匹配的内容
    //      如果想要获取匹配的值，那么可以定义一个变量接收
    //      case someThing => "something else " + someThing
    //    }
    //    //泛型擦除，在匹配的时候，和泛型无关
    //    println(describe(List(1, 2, 3, 4, 5)))
    //
    //    //数组是没有泛型擦除的，数据在匹配的时候，会保留泛型
    //    println(describe(Array(1, 2, 3, 4, 5, 6)))
    //    println(describe(Array("abc")))

    //匹配数组
    //    for (arr <- List(
    //      Array(0),
    //      Array(1, 0),
    //      Array(0, 1, 0),
    //      Array(1, 1, 0),
    //      Array(1, 1, 0, 1),
    //      Array("hello", 90))) { // 对一个数组集合进行遍历
    //      val result = arr match {
    //        case Array(0) => "0" //匹配Array(0) 这个数组
    //        case Array(x, y) => x + "," + y //匹配有两个元素的数组，然后将将元素值赋给对应的x,y
    //        case Array(0, _*) => "以0开头的数组" //匹配以0开头和数组
    //        case _ => "something else"
    //      }
    //      println("result = " + result)
    //    }

    //匹配列表
    //list是一个存放List集合的数组
    //请思考，如果要匹配 List(88) 这样的只含有一个元素的列表,并原值返回.应该怎么写
    //    for (list <- List(List(0), List(1, 0), List(0, 0, 0), List(1, 0, 0), List(88))) {
    //
    //      val result = list match {
    //        case List(0) => "0" //匹配List(0)
    //        case List(x, y) => x + "," + y //匹配有两个元素的List
    //        case List(0, _*) => "0 ..."
    //        case List(a) => a
    //        case _ => "something else"
    //      }
    //      println(result)
    //    }
    //匹配元祖
    //    //对一个元组集合进行遍历
    //    for (tuple <- List((0, 1), (1, 0), (1, 1), (1, 0, 2))) {
    //      val result = tuple match {
    //        case (0, _) => "0 ..." //是第一个元素是0的元组
    //        case (y, 0) => "" + y + "0" // 匹配后一个元素是0的对偶元组
    //        case (a, b) => "" + a + " " + b
    //        case _ => "something else" //默认
    //      }
    //      println(result)
    //    }

  }
}


