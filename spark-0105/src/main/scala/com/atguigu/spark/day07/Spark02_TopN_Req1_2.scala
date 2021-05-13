package com.atguigu.spark.day07

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * @author chujian
 * @create 2021-05-13 14:24
 */
object Spark02_TopN_Req1_2 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    // 读取数据
    val dataRDD: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\user_visit_action.txt")

    //将原始数据进行转换(分解)
    val actionRDD: RDD[UserVisitAction] = dataRDD.map {
      line => {
        val fields: Array[String] = line.split("_")
        UserVisitAction(
          fields(0),
          fields(1).toLong,
          fields(2),
          fields(3).toLong,
          fields(4),
          fields(5),
          fields(6).toLong,
          fields(7).toLong,
          fields(8),
          fields(9),
          fields(10),
          fields(11),
          fields(12).toLong
        )
      }
    }
    //CategoryCountInfo(鞋,1,0,0)
    //CategoryCountInfo(鞋,0,1,0)
    //CategoryCountInfo(鞋,0,0,1)
    //=>最终希望变成：CategoryCountInfo(鞋,1,1,1)
    //再次转换分解的数据，封装为类别点击封装 CategoryCountInfo
    //这里下单和支付操作可能会有多个品类，所以我们这里使用扁平化进行分解
    //点击、下单、支付的动作获取品类的方式是不一样的，所以需要判断不同的行为
    val infoRDD: RDD[(String, CategoryCountInfo)] = actionRDD.flatMap {
      userAction => {
        if (userAction.click_category_id != -1) {
          //点击
          List((userAction.click_category_id + "", CategoryCountInfo(userAction.click_category_id + "", 1, 0, 0)))
        } else if (userAction.order_category_ids != "null") {
          //下单
          val ids: Array[String] = userAction.order_category_ids.split(",")
          val list: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
          for (id <- ids) {
            list.append((id, CategoryCountInfo(id, 0, 1, 0)))
          }
          list
        } else if (userAction.pay_category_ids != "null") {
          //搜索
          val ids: Array[String] = userAction.pay_category_ids.split(",")
          val list: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
          for (id <- ids) {
            list.append((id, CategoryCountInfo(id, 0, 0, 1)))
          }
          list
        } else {
          Nil
        }
      }
    }

    //将reduceByKey进行预聚合处理
    val reduceRDD: RDD[(String, CategoryCountInfo)] = infoRDD.reduceByKey {
      (info1, info2) => {
        info1.clickCount = info1.clickCount + info2.clickCount
        info1.orderCount = info1.orderCount + info2.orderCount
        info1.payCount = info1.payCount + info2.payCount
        info1
      }
    }

    //转换结构为 RDD(聚合后的CategoryCountInfo)
    val mapRDD: RDD[CategoryCountInfo] = reduceRDD.map(_._2)

    //排序取前10  元组会按照顺序排序
    val resRDD: Array[CategoryCountInfo] = mapRDD
      .sortBy(info => (info.clickCount, info.orderCount, info.payCount), false)
      .take(10)

    resRDD.foreach(println)

    // 关闭连接
    sc.stop()
  }
}
