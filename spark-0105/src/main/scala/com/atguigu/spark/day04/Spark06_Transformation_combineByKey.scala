package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型combineByKey
 *
 */
object Spark06_Transformation_combineByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark06_Transformation_combineByKey").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    // 创建RDD
    val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))
    val input: RDD[(String, Int)] = sc.makeRDD(list, 2)



    //    val groupRDD: RDD[(String, Iterable[Int])] = input.groupByKey()
    //
    //    val resRDD: RDD[(String, Int)] = groupRDD.map {
    //
    //      case (name, scoreSeq) => {
    //        (name, scoreSeq.sum / scoreSeq.size)
    //      }
    //    }

    //通过combineBykey实现
    //    createCombiner: V => C, 对RDD中当前key取出第一个value做一个初始化
    //    mergeValue: (C, V) => C, 分区内计算规则，主要在分区内进行，将当前分区的value值，合并到初始化得到的c上面
    //    mergeCombiners: (C, C) => C, 分区间计算规则
    val combineRdd: RDD[(String, (Int, Int))] = input.combineByKey(
      (_, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
    )

    //打印合并后的结果
    combineRdd.collect().foreach(println)

    //计算平均值
    combineRdd.map {
      case (key, value) => {
        (key, value._1 / value._2.toDouble)
      }
    }.collect().foreach(println)

    //关闭连接
    sc.stop()
  }
}
