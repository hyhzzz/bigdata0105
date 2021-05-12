package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         转换算子 sample
 *         随机抽奖（抽取）一些，来作为数据分析的样本
 */
object Spark08_Transformation_sample {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))

    //sample方法传递三个参数
    //第一个参数表示抽取数据后是否放回，true表示放回，false表示不放回
    //第二个参数在抽取不放回的情况下，表示数据源中每条数据被抽取的概率
    //                          不是抽取数据的数量比例
    //        在抽取放回的情况的下，表示数据可能被抽取的次数
    //第三个参数表示随机数的种子，类似于打分，如果随机种子不变，那么打分的分值不变 一般不需要指定
    //                          随机数不随机，通过随机算法实现，
    //    val sampleRDD: RDD[Int] = rdd.sample(false, 0.5, 100)
    val sampleRDD: RDD[Int] = rdd.sample(true, 2, 100)


    println(sampleRDD.collect().mkString(","))

    //关闭连接
    sc.stop()

  }
}
