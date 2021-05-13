package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 7:51
 *         依赖关系
 *
 */
object Spark04_TestLineage {
  def main(args: Array[String]): Unit = {

    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    // TODO 所谓的依赖关系其实就是两个RDD之间数据处理时的分区关系
    //OneToOneDependency:一对一
    val rdd: RDD[String] = sc.makeRDD(List("hello", "scala", "spark", "hello"), 2)
    //dependencies:打印依赖关系
    println(rdd.dependencies)
    println("******")

    val mapRDD: RDD[(String, Int)] = rdd.map((_, 1))
    println(mapRDD.dependencies)
    println("******")

    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)
    println(reduceRDD.dependencies)
    println("******")

    reduceRDD.collect().foreach(println)
    //关闭连接
    sc.stop()
  }

}
