package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型sortByKey
 *         按照key对RDD中的元素进行排序
 *
 */
object Spark07_Transformation_sortByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark06_Transformation_combineByKey").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((3, "aa"), (6, "cc"), (2, "bb"), (1, "dd")))
    //sortByKey底层使用了范围分区器（RangePartitioner）
    //sortBy底层实现就是sortByKey
    //按照key的升序（默认顺序）
    rdd.sortByKey(true).collect().foreach(println)

    //按照key的降序
    //rdd.sortByKey(false).collect().foreach(println)


    //关闭连接
    sc.stop()
  }
}
