package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 18:10
 *         转换算子：双value类型
 *         并集
 *         交集
 *         差集
 *         拉链
 *
 */
object Spark12_Transformation_doubleValue {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)


    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    val rdd2: RDD[Int] = sc.makeRDD(List(3, 4, 5, 6))

    //交集 并集 差集 要求两个数据源类型保持一致的
    //交集
    val rdd3: RDD[Int] = rdd1.intersection(rdd2)


    //union
    //并集  合并
    val rdd4: RDD[Int] = rdd1.union(rdd2)


    //subtract
    //差集
    val rdd5: RDD[Int] = rdd1.subtract(rdd2)


    //通过拉链zip的方式可以将两个不同的数据源相同位置给匹配上
    /**
     * 如果两个RDD数据类型不一致怎么办？
     * 数据类型不一致可以拉链
     * 如果两个RDD数据分区不一致怎么办？
     * 如果数据分区的数量不一致，无法拉链
     * 如果两个RDD分区数量不一致怎么办？
     * 如果分区数量一致，但是分区内元素的数量不一致也无法拉链
     *
     */
    val result: RDD[(Int, Int)] = rdd1.zip(rdd2)
    result.collect().foreach(println)


    println(rdd3.collect().mkString(","))
    println(rdd4.collect().mkString(","))
    println(rdd5.collect().mkString(","))



    //关闭连接
    sc.stop()

  }
}
