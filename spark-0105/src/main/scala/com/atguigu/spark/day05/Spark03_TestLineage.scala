package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 7:51
 *         血缘关系
 *
 */
object Spark03_TestLineage {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark02_TestSerializable").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(List("hello", "scala", "spark", "hello"), 2)

    //toDebugString:打印血缘关系
    println(rdd.toDebugString)
    println("******")

    val mapRDD: RDD[(String, Int)] = rdd.map((_, 1))
    println(mapRDD.toDebugString)
    println("******")

    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)
    println(reduceRDD.toDebugString)
    println("******")

    reduceRDD.collect().foreach(println)
    //关闭连接
    sc.stop()
  }

}
