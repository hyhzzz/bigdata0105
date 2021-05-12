package com.atguigu.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 8:48
 *
 *         读取外部文件创建RDD
 *         默认分区规则
 *         math.min(分配给引用的cpu核数,2)
 *         制定分区
 */
object Spark05_Partition_file {
  def main(args: Array[String]): Unit = {

    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)


    //从本地文件中读取数据 创建RDD 默认分区的数量：默认取值为当前核数和2的最小值
    //    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\2.txt")

    //输入数据1-4，每行一个数字；输出：0=>{1、2} 1=>{3} 2=>{4} 3=>{空}
    //    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\2.txt", 3)

    //输入数据 123456 设置为3个分区
    //    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\2.txt", 3)


    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\3.txt", 5)


    //把分区保存到本地
    rdd.saveAsTextFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\output")




    //关闭Spark环境连接
    sc.stop()
  }
}
