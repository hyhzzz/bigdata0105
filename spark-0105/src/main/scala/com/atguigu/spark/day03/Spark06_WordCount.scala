package com.atguigu.spark.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 10:16
 *         使用groupBy 完成wordcount
 */
object Spark06_WordCount {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark04_Transformation_glom")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //创建RDD
    //    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark", "Hello World"))

    //简单版本 实现方式一
    //对RDD中的元素进行扁平映射
    /*   val flatMapRDD: RDD[String] = rdd.flatMap(_.split(" "))

       //将映射后的数据进行结构转换，为每个单词计数
       val mapRDD: RDD[(String, Int)] = flatMapRDD.map((_, 1))

       //按照key对RDD中的元素进行分组
       val groupByRDD: RDD[(String, Iterable[(String, Int)])] = mapRDD.groupBy(_._1)

       //对分组后的元素再次映射
       val resRDD: RDD[(String, Int)] = groupByRDD.map {
         case (word, datas) => {
           (word, datas.size)
         }
       }*/


    //简单版本实现方式二
    //对RDD中的元素进行扁平映射
    /*    val flatMapRDD: RDD[String] = rdd.flatMap(_.split(" "))

        //将RDD中的单词进行分组
        val groupRDD: RDD[(String, Iterable[String])] = flatMapRDD.groupBy(word => word)

        //对分组之后的数据再次进行映射
        val resRDD: RDD[(String, Int)] = groupRDD.map {
          case (word, datas) => {
            (word, datas.size)
          }
        }*/


    //复杂版本方式一
    //创建RDD
    /* val rdd: RDD[(String, Int)] = sc.makeRDD(List(("Hello Scala", 2), ("Hello Spark", 3), ("Hello World", 2)))

     //将原RDD中字符串以及字符串出现的自述，进行处理，形成一个新的字符串
     val rdd1: RDD[String] = rdd.map {
       case (std, count) => {
         (std + " ") * count
       }
     }

     val flatMapRDD: RDD[String] = rdd1.flatMap(_.split(" "))

     //将RDD中的单词进行分组
     val groupRDD: RDD[(String, Iterable[String])] = flatMapRDD.groupBy(word => word)

     //对分组之后的数据再次进行映射
     val resRDD: RDD[(String, Int)] = groupRDD.map {
       case (word, datas) => {
         (word, datas.size)
       }
     }
 */


    //TODO 复杂版本实现wordcount方式二
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("Hello Scala", 2), ("Hello Spark", 3), ("Hello World", 2)))

    //对RDD中元素进行扁平映射
    val newRDD: RDD[(String, Int)] = rdd.flatMap {
      case (wordStr, count) => {
        wordStr.split(" ").map((_, count))
      }
    }

    //对RDD中的元素按照元祖的第一个元素进行分组
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = newRDD.groupBy(_._1)

    //对RDD中的元素进行结构的转换
    val resRDD: RDD[(String, Int)] = groupRDD.map {
      case (word, datas) => {
        (word, datas.map(_._2).sum)
      }
    }

    resRDD.collect().foreach(println)

    //关闭连接
    sc.stop()

  }
}
