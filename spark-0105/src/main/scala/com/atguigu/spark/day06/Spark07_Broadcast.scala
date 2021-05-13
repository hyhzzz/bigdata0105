package com.atguigu.spark.day06

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         广播变了
 *         分布式共享只读变量
 *
 */
object Spark07_Broadcast {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val list: List[(String, Int)] = List(("a", 4), ("b", 5), ("c", 6))

    // 声明广播变量
    //默认情况下，数据的传递是以Task为单位，会导致内存中有大量的冗余数据，那么内存的使用效率变低
    //如果能够将数据保存在Executor端的内存中，而不是Task的内存
    //那么只需要保存一份，所有的Task就可以共享这个数据，效率就提高了
    val broadcastList: Broadcast[List[(String, Int)]] = sc.broadcast(list)

    val resultRDD: RDD[(String, (Int, Int))] = rdd1.map {
      case (k1, v1) => {

        var v2: Int = 0

        // 使用广播变量
        //for ((k3, v3) <- list.value) {
        for ((k3, v3) <- broadcastList.value) {
          if (k1 == k3) {
            v2 = v3
          }
        }

        (k1, (v1, v2))
      }
    }
    resultRDD.foreach(println)

    //4.关闭连接
    sc.stop()
  }
}