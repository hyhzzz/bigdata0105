package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 7:33
 *         广告点击Top3
 */
object Spark11_TopN {
  def main(args: Array[String]): Unit = {
    //1. 初始化Spark配置信息并建立与Spark的连接
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark11_TopN")
    val sc = new SparkContext(sparkConf)

    //2. 读取日志文件，获取原始数据
    val dataRDD: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\agent.log")

    //3. 将原始数据进行结构转换string =>(prv-adv,1)
    val prvAndAdvToOneRDD: RDD[(String, Int)] = dataRDD.map {
      line => {
        val datas: Array[String] = line.split(" ")
        (datas(1) + "-" + datas(4), 1)
      }
    }

    //4. 将转换结构后的数据进行聚合统计（prv-adv,1）=>(prv-adv,sum)
    val prvAndAdvToSumRDD: RDD[(String, Int)] = prvAndAdvToOneRDD.reduceByKey(_ + _)

    //5. 将统计的结果进行结构的转换（prv-adv,sum）=>(prv,(adv,sum))
    val prvToAdvAndSumRDD: RDD[(String, (String, Int))] = prvAndAdvToSumRDD.map {
      case (prvAndAdv, sum) => {
        val ks: Array[String] = prvAndAdv.split("-")
        (ks(0), (ks(1), sum))
      }
    }

    //6. 根据省份对数据进行分组：(prv,(adv,sum)) => (prv, Iterator[(adv,sum)])
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = prvToAdvAndSumRDD.groupByKey()

    //7. 对相同省份中的广告进行排序（降序），取前三名
    val mapValuesRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues {
      datas => {
        datas.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(3)
      }
    }

    //8. 将结果打印
    mapValuesRDD.collect().foreach(println)

    //9.关闭与spark的连接
    sc.stop()

    //关闭连接
    sc.stop()
  }
}
