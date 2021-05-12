package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子flatMap
 *         对集合中的元素进行扁平化处理
 */
object Spark03_Transformation_flatMap {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd = sc.makeRDD(List(List(1, 2), List(3, 4), List(5, 6), List(7, 8)), 2)

    //如果匿名函数输入和输出相同，那么不能简化
    val newRDD: RDD[Int] = rdd.flatMap(datas => datas)

    newRDD.collect().foreach(println)

    //关闭连接
    sc.stop()
  }
}
