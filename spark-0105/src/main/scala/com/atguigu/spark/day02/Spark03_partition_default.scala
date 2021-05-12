package com.atguigu.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 8:06
 *         默认分区
 *         从集合中创建RDD
 *         取决于分配给应用的cpu的核数    16个分区  setMaster("local[*]")
 *
 *         读取外部文件创建RDD
 *         math.min( 取决于分配给应用的cpu核数，2)取一个最小值  默认两个分区
 */
object Spark03_partition_default {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark03_partition_default")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //通过集合创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //通过读取外部文件创建RDD
    //    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input")

    //打印分区多少
    //    println(rdd.partitions.size)

    //把分区保存到本地
    rdd.saveAsTextFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\output")

    //关闭连接
    sc.stop()
  }
}
