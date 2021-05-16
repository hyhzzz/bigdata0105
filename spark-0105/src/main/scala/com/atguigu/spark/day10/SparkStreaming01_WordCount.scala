package com.atguigu.spark.day10

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author chujian
 * @create 2021-05-13 20:48
 *         使用netcat工具向9999端口不断的发送数据，通过SparkStreaming读取端口数据并统计不同单词出现的次数
 */
object SparkStreaming01_WordCount {
  def main(args: Array[String]): Unit = {
    //TODO SparkStreaming-WordCount
    //TODO 获取环境对象
    //SparkCore :SparkContext
    //SparkSQL:SparkSession
    //SparkStreaming :StreamingContext
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming01_WordCount").setMaster("local[*]")

    //StreamingContext：是SparkStreaming流式数据处理模块中的核心环境对象
    //第一个参数：表示环境配置
    //第二个参数：表示数据采集周期（批次的范围）
    val ssc = new StreamingContext(conf, Seconds(3))

    //TODO 数据处理
    //TODO 1.采集数据
    //1.数据如何采集？
    //从9999端口中采集数据
    //2.获取数据，封装为离散化数据流对象（封装）
    //离散化流的泛型表示接手后的数据类型
    // Error connecting to localhost:9999 - java.net.ConnectException: Connect
    val lineDS: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    //TODO 2.将采集的数据进行分词操作
    val wordDS: DStream[String] = lineDS.flatMap(line => line.split(" "))

    //TODO 3.将分词后的数据进行结构的转换，方便统计
    val wordToOneDS: DStream[(String, Int)] = wordDS.map(word => (word, 1))

    //TODO 4. 将转换结构后的数据进行统计
    val reduceDS: DStream[(String, Int)] = wordToOneDS.reduceByKey(_ + _)

    //TODO 5. 将统计的结果打印在控制台上
    reduceDS.print()

    //关闭环境对象
    //SparkStreaming不能关闭，因为采集器需要源源不断的采集数据
    //    ssc.stop()
    //TODO 启动数据采集
    //main方法执行完毕，那么driver程序也会结束，executor也就结束
    //start会启动新的线程执行数据采集，和deriver主线程不是一个
    //启动采集线程后，driver程序是不能停止的，需要阻塞，知道采集器停止
    ssc.start()
    //等待采集结束，终止上下文环境对象
    ssc.awaitTermination()
  }
}
