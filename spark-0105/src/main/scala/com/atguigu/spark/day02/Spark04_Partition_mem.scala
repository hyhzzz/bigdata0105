package com.atguigu.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 8:21
 *
 *         从集合中创建RDD   指定分区
 *         默认分区规则 取决于分配给应用的cpu的核数
 *         指定分区数
 *
 */
object Spark04_Partition_mem {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //通过集合创建RDD
    //1）4个数据，设置4个分区，输出：0分区->1，1分区->2，2分区->3，3分区->4
    //val rdd: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 4)

    //2）4个数据，设置3个分区，输出：0分区->1，1分区->2，2分区->3,4
    //val rdd: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 3)

    //3）5个数据，设置3个分区，输出：0分区->1，1分区->2、3，2分区->4、5
    val rdd: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4, 5), 3)

    //把分区保存到本地
    rdd.saveAsTextFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\output")

    //关闭连接
    sc.stop()
  }

}
