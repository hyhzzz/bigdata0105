package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子glom
 *         将rdd一个分区中的元素，组合成一个新的数组
 */
object Spark04_Transformation_glom {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 3, 5, 4, 2, 6), 2)

    println("--------没有glom之前-----")
    rdd.mapPartitionsWithIndex(
      (index, datas) => {
        println(index + "---->" + datas.mkString(","))
        datas
      }
    ).collect()

    println("--------调用glom之后-----")
    val newRDD: RDD[Array[Int]] = rdd.glom()

    newRDD.mapPartitionsWithIndex(
      (index, datas) => {
        println(index + "---->" + datas.next().mkString(","))
        datas
      }
    ).collect()

    //关闭连接
    sc.stop()
  }
}
