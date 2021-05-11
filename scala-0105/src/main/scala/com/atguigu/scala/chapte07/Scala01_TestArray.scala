package com.atguigu.scala.chapte07

import scala.collection.mutable.ArrayBuffer

/**
 * @author chujian
 * @create 2021-05-11 9:07
 *         不可变数组
 *         所以在执行添加或者删除操作的时候，会创建新的数组对象
 */
object Scala01_TestArray {
  def main(args: Array[String]): Unit = {

    //创建不可变数组 方式1
    val arr: Array[Int] = new Array[Int](5)
    //创建不可变数组 方式2
    //val arr: Array[Int] = Array(1,2,3,4,5)

    //访问数组中的元素  并修改数组中的元素
    arr(1) = 10
    arr.update(1, 20)
    //    println(arr(1))

    //遍历数组中的元素
    /*
    //方式1：普通的for循环
     for(i <- 0 until arr.length){
       println(arr(i))
     }
 `
    //方式2：普通的for循环
    // for( elem:Int <- arr){
     for( elem <- arr){
       println(elem)
     }

     //方式3：迭代器
     val it: Iterator[Int] = arr.iterator
     //while(it.hasNext){
     //  println(it.next())
     //}
     for (elem <- it) {
       println(elem)
     }

     //方式4：增强for循环   foreach的参数是一个和函数，是对当前集合中的元素的具体操作
     //arr.foreach((elem:Int)=>{println(elem)})
     //arr.foreach(println(_))
     arr.foreach(println)
    */

    //方式5: 用指定的字符串连接数组中的元素，形成一个新的字符串
    //println(arr.mkString("--"))


    //向数组中添加元素
    //因为Array是不可变数组，所以在执行添加操作的时候，会创建新的数组对象
    //val newArr: Array[Int] = arr. +: (30)

    //在Scala语言中，如果运算符方法中包含冒号，并且冒号在后，运算顺序为从右到左
    //val newArr: Array[Int] = arr +: 30  编译报错
    //    val newArr: Array[Int] = 30 +: arr

    //val newArr: Array[Int] = arr. :+ (20)
    //val newArr: Array[Int] = arr :+ 20

    //    println(arr.mkString("--"))
    //    println(newArr.mkString("--"))

    //（1）创建一个空的可变数组
    val arr2 = ArrayBuffer[Int]()

    //（2）追加值
    arr2.append(1, 2, 3)
    println(arr2) // 1,2,3

    //（3）ArrayBuffer ==> Array
    //（3.1）arr2.toArray 返回的结果是一个新的定长数组集合
    //（3.2）arr2它没有变化
    val newArr = arr2.toArray
    println(newArr)

    //（4）Array ===> ArrayBuffer
    //（4.1）newArr.toBuffer 返回一个变长数组 newArr2
    //（4.2）newArr 没有任何变化，依然是定长数组
    val newArr2 = newArr.toBuffer
    newArr2.append(123)

    println(newArr2)
  }
}
