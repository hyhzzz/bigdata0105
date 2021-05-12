package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型：reduceByKey
 *         根据相同的key对RDD中的元素（value）进行聚合
 *
 */
object Spark02_Transformation_partitionBy {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_partitionBy").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)


    val rdd = sc.makeRDD(List(("a", 1), ("b", 5), ("a", 5), ("b", 2)))


    //reduceByKey将数据源中每一条数据根据key将value进行聚合
    //reduceByKey会将相同的key的数据分组在一起，然后将分组后的value进行reduce聚合操作，最终获取结果
    //RDD中基本所有ByKey的方法都有shuffle操作打乱重新组合
    val newRDD: RDD[(String, Int)] = rdd.reduceByKey(_ + _)
    newRDD.collect().foreach(println)
    //关闭连接
    sc.stop()
  }

}