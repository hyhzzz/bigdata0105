package com.atguigu.spark.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         累加器
 *
 */
object Spark06_Accumulator {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //创建RDD   单值RDD，直接对数据进行求和，不存在shuffle过程
    //    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //    println(rdd.sum())
    //    println(rdd.reduce(_ + _))

    //默认情况下 Driver声明的变量可以传递到Executor计算
    //当是Executor端执行的结果不会返回到Driver,所以打印的结果不变
    //可以通知spark将结果返回到Driver，采用特殊的数据结构：累加器

    // TODO 声明longAccumulator 累加器（分布式共享只写变量）
    //概念中的只写，并不是表示变量的值不能读取，只能写
    //表示的是不同的累加器之间无法读取的。只能修改（增加）数据
    //只有Driver才可以将所有的累加器的值读取出来，并进行聚合
    //累加器是一个变量，可以被spark识别，可以在计算完毕后，将结果由spark返回到Driver端

    //存在shuffle 效率较低
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 4)))
    //    val resRDD: RDD[(String, Int)] = rdd.reduceByKey(_ + _)
    //    resRDD.map(_._2).collect().foreach(println)

    //如果定义普通的变量，那么在Driver定义，Excutor会创建变量的副本，算子都是对副本进行操作，Driver端的变量不会更新
    /*var sum: Int = 0
    rdd.foreach {
      case (word, count) => {
        sum += count
      }
    }
    println(sum)*/

    //如果需要通过Excutor对Driver端定义的变量进行更新，那么需要定义累加器
    var sum = sc.longAccumulator("sum") //整数类型
    //    sc.doubleAccumulator() //浮点类型
    //    sc.collectionAccumulator()//集合类型
    rdd.foreach{
      case (word, count) => {
        //TODO 使用累加器
        //sum += count
        sum.add(count)
      }
    }
    // 获取累加器的值
    println(sum.value)


    // 关闭连接
    sc.stop()
  }
}