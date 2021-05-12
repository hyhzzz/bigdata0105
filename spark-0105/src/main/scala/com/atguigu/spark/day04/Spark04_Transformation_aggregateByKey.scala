package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型aggregateByKey
 *         按照key对分区内以及分区间的数据进行处理
 *
 */
object Spark04_Transformation_aggregateByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_groupByKey").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)

    //aggregateByKey方法表示根据key对value进行聚合
    // reduceByKey表示分区内计算规则和分区间的计算规则相同
    //在某些逻辑处理中，分区内计算规则和分区间计算规则是不相同的。
    //此时使用reduceByKey是不合适的。
    //aggregateByKey可以实现分区内和分区间不一样的处理

    //取出每个分区内相同key的最大值然后分区间相加
    //函数柯里化
    //aggregateByKey有两个参数列表
    //第一个参数列表传递一个参数
    //这个参数表示分区内计算的初始值
    //第二个参数列表传递两个参数
    //第一个参数表示的是分区内的计算规则
    //第二个分区表示的是分区间的计算规则

    //scala中的数据计算基本上都是两两计算
//    rdd.aggregateByKey(0)(
//      (x, y) => math.max(x, y),
//      (x, y) => x + y
//    ).collect().foreach(println)
    rdd.aggregateByKey(0)(math.max(_, _), _ + _).collect().foreach(println)

    //关闭连接
    sc.stop()
  }

}