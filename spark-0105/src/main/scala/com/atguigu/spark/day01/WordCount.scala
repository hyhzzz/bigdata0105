package com.atguigu.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-11 23:24
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    /*    //TODO 1.创建SparkConf配置文件并设置App名称
        //local  这样写只会有一个线程  local[*] 根据cpu核数进行分配   local[5]  固定5个线程
        //setMaster表示和谁连     local表示本机spark环境    setAppName：应用程序的名称
        val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")

        //TODO 2.创建SparkContext，该对象是提交Spark App的入口
        val sc = new SparkContext(conf)

        //TODO 3.读取外部文件  textFile:读取文件，获取文件的原始数据（一行一行读）
        val textRDD: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\1.txt")

        //TODO 4.对读取到的数据进行切割并进行将扁平化操作。string =>word(分词)
        val flatMapRDD: RDD[String] = textRDD.flatMap(_.split(" "))

        //TODO 5.对数据集中的数据进行结构的转换  计数
        val mapRDD: RDD[(String, Int)] = flatMapRDD.map((_, 1))

        //TODO 6.对相同的单词出现的次数 进行汇总
        val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)

        //TODO 7.将统计结果采集到控制台打印
        val res: Array[(String, Int)] = reduceRDD.collect()
        res.foreach(println)*/


    //一行代码搞定
    //    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    //    val sc = new SparkContext(conf)
    //    sc.textFile("D:\\\\idea\\\\wokspace\\\\bigdata-0105\\\\spark-0105\\\\input\\\\1.txt")
    //      .flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect().foreach(println)

    //再次优化  输入和输出不写死
    //1.创建SparkConf并配置文件
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc = new SparkContext(conf)
    //一行代码搞定
    sc.textFile(args(0))
      .flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile(args(1))


    //TODO 释放资源
    sc.stop()
  }

}
