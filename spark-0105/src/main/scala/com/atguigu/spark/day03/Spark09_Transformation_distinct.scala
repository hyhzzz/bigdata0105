package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 18:10
 *         转换算子：distinct
 *         将数据集中重复的数据去重
 */
object Spark09_Transformation_distinct {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 1, 5, 2, 3, 9, 6, 1), 3)

    //SparkRDD核心去重代码
    // map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
    // val newRDD: RDD[Int] = rdd.distinct()//默认
    //去重之后 可以指定新的RDD分区

    rdd.mapPartitionsWithIndex(
      (index, datas) => {
        println(index + "-->" + datas.mkString(","))
        datas
      }
    ).collect()

    println("-------------")

    val newRDD: RDD[Int] = rdd.distinct(2)
    //    val newRDD: RDD[Int] = rdd.distinct()

    newRDD.mapPartitionsWithIndex(
      (index, datas) => {
        println(index + "-->" + datas.mkString(","))
        datas
      }
    ).collect()

    //    newRDD.collect().foreach(println)
    //关闭连接
    sc.stop()

  }
}
