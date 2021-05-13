package com.atguigu.spark.day06

import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.{SparkConf, SparkContext}

import java.sql.DriverManager

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         从MySQL数据库中读取数据
 *
 */
object Spark04_MYSQL {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //定义连接mysql的参数
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://hadoop102:3306/rdd"
    val userName = "root"
    val passWd = "123456"

    //创建JdbcRDD
    val rdd = new JdbcRDD(sc, () => {
      Class.forName(driver)
      DriverManager.getConnection(url, userName, passWd)
    },
      "select * from `rddtable` where `id`>=? and `id`<=?;",
      1,
      10,
      1,
      r => (r.getInt(1), r.getString(2))
    )

    //打印最后结果
    println(rdd.count())
    rdd.foreach(println)

    //4.关闭连接
    sc.stop()
  }
}
