package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型:cogroup
 *         操作两个RDD中的KV元素，每个RDD中相同key中的元素分别聚合成一个集合
 *
 */
object Spark10_Transformation_cogroup {
  def main(args: Array[String]): Unit = {

    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark10_Transformation_cogroup").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3具体业务逻辑
    //3.1 创建第一个RDD
    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((1,"a"),(2,"b"),(3,"c")))

    //3.2 创建第二个RDD
    val rdd1: RDD[(Int, Int)] = sc.makeRDD(Array((1,4),(2,5),(3,6)))

    //3.3 cogroup两个RDD并打印结果
    rdd.cogroup(rdd1).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
