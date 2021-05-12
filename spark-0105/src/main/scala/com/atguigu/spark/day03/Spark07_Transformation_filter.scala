package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子 filter
 *         按照指定的过滤规则对RDD中的元素进行过滤
 */
object Spark07_Transformation_filter {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    /*   val rdd: RDD[String] = sc.makeRDD(List("hello scala", "hello spark"), 1)

       val newRDD: RDD[String] = rdd.filter(_.contains("hello"))

       newRDD.collect().foreach(println)*/

    //将数据源中的每一条数据，根据过滤规则对数据进行筛选过滤
    //如果符合规则（true）数据保留，不符合规则（false）数据丢弃
    // filter方法可以将数据从多变少
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7), 2)

    val resultRDD: RDD[Int] = rdd.filter(_ % 2 != 0)

    resultRDD.collect().foreach(println)

    //关闭连接
    sc.stop()

  }
}
