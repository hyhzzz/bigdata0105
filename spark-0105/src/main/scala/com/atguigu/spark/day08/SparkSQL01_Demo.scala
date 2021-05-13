package com.atguigu.spark.day08

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
 * @author chujian
 * @create 2021-05-13 18:12
 *         演示RDD&DS之间的关系以及转换
 */
object SparkSQL01_Demo {
  def main(args: Array[String]): Unit = {
    //创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01_Demo")

    //创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //spark不是包名，是上下文环境对象名
    import spark.implicits._

    //读取json文件 创建DataFrame  {"username": "lisi","age": 18}
    val df: DataFrame = spark.read.json("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\json.txt")
    //df.show()

    //SQL风格语法
    df.createOrReplaceTempView("user")
    //spark.sql("select avg(age) from user").show

    //DSL风格语法
    //df.select("username","age").show()

    //*****RDD=>DataFrame=>DataSet*****
    //RDD
    val rdd1: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "qiaofeng", 30), (2, "xuzhu", 28), (3, "duanyu", 20)))

    //DataFrame
    val df1: DataFrame = rdd1.toDF("id", "name", "age")
    //df1.show()

    //DateSet
    val ds1: Dataset[User] = df1.as[User]
    //ds1.show()

    //*****DataSet=>DataFrame=>RDD*****
    //DataFrame
    val df2: DataFrame = ds1.toDF()

    //RDD  返回的RDD类型为Row，里面提供的getXXX方法可以获取字段值，类似jdbc处理结果集，但是索引从0开始
    val rdd2: RDD[Row] = df2.rdd
    //rdd2.foreach(a=>println(a.getString(1)))

    //*****RDD=>DataSe*****
    rdd1.map {
      case (id, name, age) => User(id, name, age)
    }.toDS()

    //*****DataSet=>=>RDD*****
    ds1.rdd

    //释放资源
    spark.stop()

  }
}

case class User(int: Int, name: String, age: Int)
