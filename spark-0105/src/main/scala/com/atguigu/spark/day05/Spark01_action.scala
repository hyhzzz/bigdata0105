package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 7:51
 *         行动算子：reduce
 *         聚集RDD中的所有元素，先聚合分区内数据，再聚合分区间数据
 */
object Spark01_action {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark01_action_reduce").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3具体业务逻辑
    //3.1 创建第一个RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //3.2 聚合数据
    //    val reduceResult: Int = rdd.reduce(_ + _)
    //    println(reduceResult)

    //collect  以数组Array的形式返回数据集的所有元素
    //    rdd.collect().foreach(println)

    //count 返回RDD中元素的个数
    //    val countResult: Long = rdd.count()
    //    println(countResult)

    //first 返回RDD中的第一个元素
    //    val firstResult: Int = rdd.first()
    //    println(firstResult)

    //take 返回一个由RDD的前n个元素组成的数组
    //    val takeResult: Array[Int] = rdd.take(2)
    //    println(takeResult.mkString(" "))

    // aggregate 将该RDD所有元素相加得到结果
    //val result: Int = rdd.aggregate(0)(_ + _, _ + _)
    //    val result: Int = rdd.aggregate(10)(_ + _, _ + _)
    //    println(result)

    //将该RDD所有元素相加得到结果
    //    val foldResult: Int = rdd.fold(0)(_+_)
    //    println(foldResult)

    //countByKey
    //    val rdd: RDD[(Int, String)] = sc.makeRDD(List((1, "a"), (1, "a"), (1, "a"), (2, "b"), (3, "c"), (3, "c")))
    //    val result: collection.Map[Int, Long] = rdd.countByKey()
    //    println(result)


    //save  保存成Sequencefile文件
    //保存成Text文件
    //    rdd.saveAsTextFile("output")
    //序列化成对象保存到文件
    //    rdd.saveAsObjectFile("output1")
    // 保存成Sequencefile文件
    //    rdd.map((_, 1)).saveAsSequenceFile("output2")


    //foreach 分布式遍历RDD中的每一个元素，调用指定函数
    rdd.map(num => num).collect().foreach(println)
    println("****************")
    //分布式打印
    rdd.foreach(println)

    //关闭连接
    sc.stop()
  }
}
