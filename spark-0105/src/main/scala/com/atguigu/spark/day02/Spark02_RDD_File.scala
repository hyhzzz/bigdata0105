package com.atguigu.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 7:58
 *         通过读取外部文件创建RDD
 */
object Spark02_RDD_File {
  def main(args: Array[String]): Unit = {

    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //从本地文件中读取数据 创建RDD
    //    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\1.txt")

    //从hdfs服务器上读取数据 ，创建RDD
    val rdd: RDD[String] = sc.textFile("hdfs://hadoop102:8020/input")

    rdd.collect().foreach(println)

    //关闭连接
    sc.stop()
  }

}
