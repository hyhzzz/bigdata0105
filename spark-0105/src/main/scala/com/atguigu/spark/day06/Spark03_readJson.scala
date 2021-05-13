package com.atguigu.spark.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         读取json格式数据
 *
 */
object Spark03_readJson {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //创建RDD
    val rdd: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\json.txt")

    // 导入解析Json所需的包并解析Json
    import scala.util.parsing.json.JSON
    val resultRDD: RDD[Option[Any]] = rdd.map(JSON.parseFull)

    resultRDD.collect().foreach(println)


    //4.关闭连接
    sc.stop()
  }
}
