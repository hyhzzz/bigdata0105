package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子mapPartitions
 */
object Spark01_Transformation_mapPartitions {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    //以一个分区为单位进行转换映射，而不是一个数据一个数据进行转换映射
    //效率非常改高，类似于批处理
    val newRDD: RDD[Int] = rdd.mapPartitions(datas => {
      datas.map(_ * 2)
    })

    newRDD.collect().foreach(println)

    //关闭连接
    sc.stop()
  }
}
