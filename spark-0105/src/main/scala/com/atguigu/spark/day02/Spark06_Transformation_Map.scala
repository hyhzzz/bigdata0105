package com.atguigu.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 9:28
 */
object Spark06_Transformation_Map {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)


    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    val newRDD: RDD[Int] = rdd.map(_ * 2)
    newRDD.collect().foreach(println)

    //关闭连接
    sc.stop()
  }

}
