package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型：groupByKey
 *         根据key对RDD中的元素进行分组
 *
 */
object Spark03_Transformation_groupByKey {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_groupByKey").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)


    val rdd = sc.makeRDD(List(("a", 1), ("b", 5), ("a", 5), ("b", 2)))

    //groupByKey：根据数据的key进行分组，只要key相同，那么数据就在一个组中
    //groupByKey方法的返回值结果类型为元祖
    //元祖的第一个元素其实就是分组的key
    //元祖的第二个元素其实就是相同key的value的集合

    val groupRDD: RDD[(String, Iterable[Int])] = rdd.groupByKey()


    val resRDD: RDD[(String, Int)] = groupRDD.map {
      case (key, datas) => {
        (key, datas.sum)
      }
    }
    resRDD.collect().foreach(println)

    //关闭连接
    sc.stop()
  }

}