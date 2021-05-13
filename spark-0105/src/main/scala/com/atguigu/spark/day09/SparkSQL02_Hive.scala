package com.atguigu.spark.day09

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author chujian
 * @create 2021-05-13 20:18
 */
object SparkSQL02_Hive {
  def main(args: Array[String]): Unit = {
    //创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01_Demo")
    val spark: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .master("local[*]")
      .appName("SQLTest")
      .getOrCreate()
    spark.sql("show tables").show()
    //释放资源
    spark.stop()
  }
}
