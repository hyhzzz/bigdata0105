package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子mapPartitionsWithIndex
 *         以分区为单位，对rdd中的元素进行映射，并且带分区编号
 */
object Spark02_Transformation_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 3)

    //mapPartitionsWithIndex 以分区为单位进行数据处理，并同时提供分区号
    //mapPartitionsWithIndex传递的匿名函数需要传递两个参数
    //第一个参数：表示分区索引编号
    //第二个参数：表示分区数据的可迭代集合
    //匿名函数的返回值类型为可迭代的集合
    /*   val newRDD: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex(
         (index, datas) => {
           datas.map((index, _))
         }
       )*/

    //第二个分区的元素*2 ,其余分区数据保持不变
    val newRDD: RDD[Int] = rdd.mapPartitionsWithIndex(
      (index, datas) => {
        index match {
          case 1 => datas.map(_ * 2)
          case _ => datas
        }
      }
    )


    newRDD.collect().foreach(println)

    //关闭连接
    sc.stop()
  }
}
