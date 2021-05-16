package com.atguigu.spark.day10

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author chujian
 * @create 2021-05-13 21:05
 *         通过receiverapi连接kafka数据源，获取数据
 */
object SparkStreaming02_ReceiverAPI {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf
    val sparkConf: SparkConf = new SparkConf().setAppName("Spark05_DirectAPI_Auto01").setMaster("local[*]")

    //2.创建StreamingContext
    val ssc = new StreamingContext(sparkConf, Seconds(3))


    //3.使用ReceiverAPI读取Kafka数据创建DStream
    val kafkaDStream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,
      "hadoop102:2181,hadoop203:2181,hadoop204:2181",
      "bigdata",
      //v表示的主题的分区数
      Map("mybak" -> 2))

    //4. 计算WordCount并打印        new KafkaProducer[String,String]().send(new ProducerRecord[]())
    val lineDStream: DStream[String] = kafkaDStream.map(_._2)
    val word: DStream[String] = lineDStream.flatMap(_.split(" "))
    val wordToOneDStream: DStream[(String, Int)] = word.map((_, 1))
    val wordToCountDStream: DStream[(String, Int)] = wordToOneDStream.reduceByKey(_ + _)
    wordToCountDStream.print()

    //5. 开启任务
    ssc.start()
    ssc.awaitTermination()

  }
}
