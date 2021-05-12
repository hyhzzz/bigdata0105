package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 18:10
 *         转换算子：sortBy 排序
 *
 */
object Spark11_Transformation_sortBy {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)


    //创建RDD
//    val rdd: RDD[Int] = sc.makeRDD(List(1,2,5,4,3,6))
val rdd: RDD[String] = sc.makeRDD(List("1", "22", "12", "2", "3"))
    //sortBy根据指定的规则对数据进行排序，默认是升序排列（从小到大）
    //第二个参数表示排序规则，默认为true，如果为false表示降序
//    val newRDD: RDD[Int] = rdd.sortBy(num => num)



    //字符串默认的排序方式为字典顺序
    val result: RDD[String] = rdd.sortBy(s => s.toInt)
    println(result.collect().mkString(","))

//    result.collect().foreach(println)

    //关闭连接
    sc.stop()

  }
}
