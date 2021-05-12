package com.atguigu.spark.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-12 19:25
 *         key-value类型：partitionBy
 *         对kv类型的RDD按照key进行重新分区
 *
 */
object Spark01_Transformation_partitionBy {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)


    val rdd: RDD[(Int, String)] = sc.makeRDD(List((1, "aaa"), (2, "bbb"), (3, "ccc")), 3)

    //partitionBy方法表示根据指定的规则对数据进行重分区
    //partitionBy方法需要对特定的数据类型(K-V)进行处理
    //partitionBy需要传递一个分区器对象，主要用于对数据进行分区
    //        Spark默认提供了两个分区器：
    //                    默认是HashPartitioner
    //                         RangePartitioner

    //RDD类中没有partitionBy这个方法，但是可以通过隐士转换规则，转换为其他的类调用partitionBy方法
    //转换的规则是数据类型必须是键值类型

    rdd.mapPartitionsWithIndex {
      (index, dastas) => {
        println(index + "-->" + dastas.mkString(","))
        dastas
      }
    }.collect()

    println("--------------------")

    //    val newRDD: RDD[(Int, String)] = rdd.partitionBy(new HashPartitioner(2))

    //自定义分区器
    val newRDD: RDD[(Int, String)] = rdd.partitionBy(new MyPartitioner(2))

    newRDD.mapPartitionsWithIndex {
      (index, dastas) => {
        println(index + "-->" + dastas.mkString(","))
        dastas
      }
    }.collect()

    /**
     *
     * 如果重分区的分区器和当前RDD的分区器一样怎么办？
     * 如果分区器一样，RDD不会有任何变化，直接返回RDD本身
     * Spark还有其他分区器吗？
     * RangePartition（范围分区器）每个分区其实是连续的范围
     * PythonPartition
     * 如果想按照自己的方法进行数据分区怎么办？
     * 自定义分区器
     */

    //关闭连接
    sc.stop()
  }
}

//自定义分区器
class MyPartitioner(num: Int) extends Partitioner {
  // 设置的分区数
  override def numPartitions: Int = num

  // 具体分区逻辑  返回值int表示分区编号，从0开始
  override def getPartition(key: Any): Int = {

    if (key.isInstanceOf[Int]) {

      val keyInt: Int = key.asInstanceOf[Int]
      if (keyInt % 2 == 0)
        0
      else
        1
    } else {
      0
    }
  }
}