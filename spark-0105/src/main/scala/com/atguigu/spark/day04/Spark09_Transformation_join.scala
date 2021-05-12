package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型join
 *         join算子相当于内连接，将两个RDD中的key相同的数据匹配出来，如果key匹配不上。那么数据不关联
 *
 */
object Spark09_Transformation_join {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark09_Transformation_join").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[(Int, String)] = sc.makeRDD(Array((1, "a"), (2, "b"), (3, "c")))
    val rdd2: RDD[(Int, Int)] = sc.makeRDD(Array((1, 4), (2, 5), (4, 6)))


    //将数据源中的相同key的数据进行连接，形成value的元祖
    //如果两个数据源中没有匹配key，那么当前的数据无法连接
    //join方法会产生笛卡尔积，数据会几何增长，性能非常低，能不用尽量不用

    //    val newRDD: RDD[(Int, (String, Int))] = rdd1.join(rdd2)
    rdd1.leftOuterJoin(rdd2).collect().foreach(println)
    //    rdd1.rightOuterJoin(rdd2).collect().foreach(println)
    //    newRDD.collect().foreach(println)


    //关闭连接
    sc.stop()
  }
}
