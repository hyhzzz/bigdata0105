package com.atguigu.spark.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         RDD的检查点
 *
 */
object Spark02_checkpoint {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //创建RDD
    val rdd: RDD[String] = sc.makeRDD(List("hello chujian", "hello xinshang"), 2)


    //需要设置路径，否则抛异常：Checkpoint directory has not been set in the SparkContext
    //一般设定检查点路径都是分布式存储系统的路径如：HDFS
    //设置访问HDFS集群的用户名
    //    System.setProperty("HADOOP_USER_NAME","atguigu")
    sc.setCheckpointDir("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\cp")

    val flatRDD: RDD[String] = rdd.flatMap(line => {
      println("**************")
      line.split(" ")
    })

    //TODO 检查点
    //checkpoint和行动算子的操作不一样，所以需要独立在执行一次作业
    //因为checkpoint会导致多次执行相同的作业，为了提高效率，可以先执行缓存操作，在进行检查点操作
    // 增加缓存,避免再重新跑一个job做checkpoint
    flatRDD.cache()
    flatRDD.checkpoint()
    println(flatRDD.toDebugString)

    flatRDD.collect().foreach(println)
    println("************************")
    //println(flatRDD.toDebugString)
    //flatRDD.collect().foreach(println)

    //Spark性能的快慢主要取决于shuffle的性能
    //shuffle会落盘，所以性能比较差，那如果重复执行相同的shuffle操作，性能会更差
    //所以spark对于shuffle操作都会自动添加缓存操作。

    //4.关闭连接
    sc.stop()
  }
}
