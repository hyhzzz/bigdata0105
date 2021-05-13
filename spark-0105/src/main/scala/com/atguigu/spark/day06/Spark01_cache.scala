package com.atguigu.spark.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         RDD的缓存
 *
 *         //RDD重用:效率是不会提高的。
 *         //RDD不会存储数据，无法继续向后执行，如果想要重复使用，其实也是从头执行
 *
 *         //为了能够提高RDD的执行效率，在RDD重用时采用持久化操作，优化访问，可以不用从头执行
 *
 *         //缓存操作会在执行后给血缘关系中添加依赖(CachedPartitions)
 *         //RDD重复使用时，会通过缓存的依赖，获取数据继续计算，效率就会提高
 *
 *         //cache表示缓存，会将数据保存在内存中
 *         //存在问题：1数据不安全，2容量不够
 *         //可以将数据保存成文件进行持久化操作。
 *         //    flatRDD.cache()			//主要用cache，提高运行效率
 *
 *         //底层源码中，cache就是调用persist方法，所以默认的持久化就是缓存操作。StorageLevel.MEMORY_ONLY 默认级别
 *         //如果想要改变存储的方式为位置，可以更改存储级别
 *
 *         //当前的持久化操作只对当前的应用程序起作用，如果希望多个应用程序可以共享数据
 *         //中间计算结果想要在其他场合使用，这个操作是无法处理的。
 *         flatRDD.persist(StorageLevel.DISK_ONLY)	//用的不多 会落盘 有IO
 *
 *         //如果想要跨应用程序共享数据，重复使用，那么需要采用特殊的操作：检查点
 */
object Spark01_cache {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //创建RDD
    val rdd: RDD[String] = sc.makeRDD(List("hello chujian", "hello xinshang"), 2)


    //扁平映射
    val wordRdd: RDD[String] = rdd.flatMap(_.split(" "))

    //结构转换
    val mapRDD: RDD[(String, Int)] = wordRdd.map {
      word => {
        println("************")
        (word, 1)
      }
    }
    // cache操作会增加血缘关系，不改变原有的血缘关系
    println(mapRDD.toDebugString)

    //对RDD的数据进行缓存，底层调用的是persist函数，默认缓存在内存中
    //    mapRDD.cache()

    //persistk可以接收参数，指定缓存位置 更改存储级别
    //注意：虽然叫持久化，但是当应用程序执行完之后，缓存的目录也会被删除
    mapRDD.persist(StorageLevel.MEMORY_AND_DISK_2)

    //触发执行操作
    mapRDD.collect()

    println("-----------------")

    //打印血缘关系
    println(mapRDD.toDebugString)

    //再次触发执行操作
    mapRDD.collect()

    //关闭连接
    sc.stop()
  }
}
