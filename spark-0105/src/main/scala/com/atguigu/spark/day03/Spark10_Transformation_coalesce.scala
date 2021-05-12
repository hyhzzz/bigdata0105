package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 18:10
 *         转换算子：coalesce
 *         重新分区
 *         根据数据量缩减分区，用于大数据集过滤后，提高小数据集的执行效率
 *
 *         //如果缩减分区就采用coalesce来实现，如果希望打散数据就采用shuffle。 想把分区数变少就用这个
 *         //如果扩大分区就采用repartition来实现。肯定有shuffle    想把分区数变大就用这个
 */
object Spark10_Transformation_coalesce {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)


    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 3)

    rdd.mapPartitionsWithIndex(
      (index, datas) => {
        println(index + "-->" + datas.mkString(","))
        datas
      }
    ).collect()

    println("----------------------")

    //过滤数据后，可能会导致数据急剧减少，那么数据处理的效率非常低，浪费资源(数据量少还分布式执行，不断去传输)
    //所以为了提高效率，可以缩减分区
    //    rdd.filter(_ % 2 == 0)
    //coalesce方法有多个参数
    //第一个参数表示改变分区的数量。
    //第二个参数表示分区的数量在改变时，是否使用shuffle操作，默认不使用shuffle
    //    val newRDD: RDD[Int] = rdd.coalesce(2)
    //如果不适用shuffle的场景下，那么coalesce不能扩大分区，如果想要扩大分区必须使用shuffle
    //    val newRDD: RDD[Int] = rdd.coalesce(2, true)
    val newRDD: RDD[Int] = rdd.repartition(4)

    newRDD.mapPartitionsWithIndex(
      (index, datas) => {
        println(index + "-->" + datas.mkString(","))
        datas
      }
    ).collect()

    //    newRDD.collect().foreach(println)

    //关闭连接
    sc.stop()

  }
}
