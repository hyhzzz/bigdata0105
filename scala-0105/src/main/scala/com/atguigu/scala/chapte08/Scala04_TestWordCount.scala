package com.atguigu.scala.chapte08

/**
 * @author chujian
 * @create 2021-05-11 12:35
 * 复杂版本WordCount
 */
object Scala04_TestWordCount {
  def main(args: Array[String]): Unit = {
    //复杂版本  方式1
    val tupleList = List(("Hello Scala Spark World ", 4), ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))

    //将元组(字符串，次数)  进行转换为一个大的字符串
    val newList: List[String] = tupleList.map(
      kv => {
        (kv._1.trim + " ") * kv._2
      }
    )
    //扁平映射
    val flatList: List[String] = newList.flatMap(_.split(" "))
    println(flatList)


  }
}

