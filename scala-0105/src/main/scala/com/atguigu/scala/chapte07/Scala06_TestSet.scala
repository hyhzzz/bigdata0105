package com.atguigu.scala.chapte07

import scala.collection.mutable

/**
 * @author chujian
 * @create 2021-05-11 10:39
 *         set集合
 *         无序、不能重复
 */
object Scala06_TestSet {
  def main(args: Array[String]): Unit = {
    /*
       //不可变
       val set: Set[Int] = Set(1,2,5,3,2,4,3,1,6)

       //添加元素
       val set1: Set[Int] = set.+(20)

       println(set)
       println(set1)
       */

    //可变set
    val set: mutable.Set[Int] = mutable.Set(1, 2, 3, 4)

    //向集合中添加元素
    val flag: Boolean = set.add(10)
    println(set)
    println(flag)

    //删除元素
    set.remove(2)
  }
}
