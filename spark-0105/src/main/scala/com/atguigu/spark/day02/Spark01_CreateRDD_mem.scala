package com.atguigu.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 7:32
 *         通过读取内存集合中的数据，创建RDD
 */
object Spark01_CreateRDD_mem {
  def main(args: Array[String]): Unit = {

    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark01_CreateRDD_mem")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //创建集合对象
    val list: List[Int] = List(1, 2, 3, 4)

    //根据集合创建RDD   方式一
    //    val rdd: RDD[Int] = sc.parallelize(list)

    //方式二 底层调用的就是parallelize
    val rdd: RDD[Int] = sc.makeRDD(list)

    //打印输出
    rdd.collect().foreach(println)

    //关闭连接
    sc.stop()
  }
}
