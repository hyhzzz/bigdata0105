package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型foldByKey
 *         foldByKey(初始值)（分区内和间计算规则）
 *
 */
object Spark05_Transformation_foldByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_groupByKey").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)


    //aggregateByKey如果分区内核分区间的计算规则相同，那么spark foldByKey可以简化这个操作
    //    rdd.aggregateByKey(10)(
    //      (x: Int, y: Int) => math.max(x, y),
    //      (x: Int, y: Int) => x + y
    //    ).collect().foreach(println)

    rdd.foldByKey(0)(_ + _).collect().foreach(println)

    //关闭连接
    sc.stop()
  }

}