package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型mapValues
 *         对kv类型的RDD中的value部分进行映射
 *
 */
object Spark08_Transformation_mapValues {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark06_Transformation_combineByKey").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((1, "a"), (1, "d"), (2, "b"), (3, "c")))


    //对value添加字符串"|||"
    rdd.mapValues(_ + "|||").collect().foreach(println)


    //关闭连接
    sc.stop()
  }
}
