package com.atguigu.spark.day09

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}

import java.util.Properties

/**
 * @author chujian
 * @create 2021-05-13 19:48
 *         通过jdbc对MySQL进行读写操作
 */
object SparkSQL01_MySQL {
  def main(args: Array[String]): Unit = {
    //创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01_Demo")

    //创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._

    //从MySQL数据库中读取数据方式一
//     spark.read.format("jdbc")
    //       .option("url", "jdbc:mysql://hadoop102:3306/test")
    //       .option("driver", "com.mysql.jdbc.Driver")
    //       .option("user", "root")
    //       .option("password", "123456")
    //       .option("dbtable", "*/ user")
    //      .load().show

    //方式2:通用的load方法读取 参数另一种形式
    /*
        spark.read.format("jdbc")
          .options(Map("url" -> "jdbc:mysql://hadoop102:3306/test?user=root&password=123456",
            "dbtable" -> "user", "driver" -> "com.mysql.jdbc.Driver")).load().show
    */

    //方式3:使用jdbc方法读取
    /*    val props: Properties = new Properties()
        props.setProperty("user", "root")
        props.setProperty("password", "123456")
        val df: DataFrame = spark.read.jdbc("jdbc:mysql://hadoop102:3306/test", "user", props)
        df.show*/

    //向MySQL数据库中写入数据
    val rdd: RDD[User2] = spark.sparkContext.makeRDD(List(User2("lisi", 20), User2("zs", 30)))
    //将RDD转换为DS
    val ds: Dataset[User2] = rdd.toDS
    //方式1：通用的方式  format指定写出类型
    /* ds.write
       .format("jdbc")
       .option("url", "jdbc:mysql://hadoop102:3306/test")
       .option("user", "root")
       .option("password", "123456")
       .option("dbtable", "user")
       .mode(SaveMode.Append)
       .save()*/

    //方式2：通过jdbc方法
    val props: Properties = new Properties()
    props.setProperty("user", "root")
    props.setProperty("password", "123456")
    ds.write.mode(SaveMode.Append).jdbc("jdbc:mysql://hadoop102:3306/test", "user", props)

    //释放资源
    spark.stop()
  }
}

case class User2(name: String, age: Long)