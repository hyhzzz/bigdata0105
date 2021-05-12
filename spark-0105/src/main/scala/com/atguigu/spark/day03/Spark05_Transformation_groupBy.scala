package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子groupBy
 *         按照一定的分组规则对RDD中的元素进行分组, 分区默认不变，但是数据会被打乱重新组合，我们将这样的操作称之为==shuffle
 */
object Spark05_Transformation_groupBy {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    /* val rdd: RDD[Int] = sc.makeRDD(List(1, 3, 5, 4, 2, 6, 7, 8, 9), 2)

     println("====分组之前")
     rdd.mapPartitionsWithIndex(
       (index, datas) => {
         println(index + "-->" + datas.mkString(","))
         datas
       }
     ).collect()

     val newRDD: RDD[(Int, Iterable[Int])] = rdd.groupBy(_ % 3)

     println("====分组之后")
     newRDD.mapPartitionsWithIndex(
       (index, datas) => {
         println(index + "-->" + datas.mkString(","))
         datas
       }
     ).collect()*/

    val rdd: RDD[String] = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop", "hello", "hbase"))
    val newRDD: RDD[(String, Iterable[String])] = rdd.groupBy(elem => elem)

    newRDD.collect().foreach(println)

    Thread.sleep(10000)

    //关闭连接
    sc.stop()
    
  }
}
