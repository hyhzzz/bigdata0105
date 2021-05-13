package com.atguigu.spark.day07

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * @author chujian
 * @create 2021-05-13 12:21
 *         需求一：统计热门品类TopN
 */
object Spark01_TopN_req1 {
  def main(args: Array[String]): Unit = {
    //创建SparkSparkConf并设置APP名称
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    //创建SparkContext对象，这个对象是提交SparkApp的入口
    val sc: SparkContext = new SparkContext(conf)

    //1.读取数据，创建RDD
    val dataRDD: RDD[String] = sc.textFile("D:\\idea\\wokspace\\bigdata-0105\\spark-0105\\input\\user_visit_action.txt")

    //2. 将读到的数据进行切分，并且将切分的内容封装为UserVisitAction对象
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
    //3.判断当前日志记录的是什么行为，并且封装为结果对象
    val infoRDD: RDD[CategoryCountInfo] = actionRDD.flatMap {
      userAction => {
        if (userAction.click_category_id != -1) {
          //点击
          List(CategoryCountInfo(userAction.click_category_id + "", 1, 0, 0))
        } else if (userAction.order_category_ids != "null") {
          //下单
          val ids: Array[String] = userAction.order_category_ids.split(",")
          val list: ListBuffer[CategoryCountInfo] = new ListBuffer[CategoryCountInfo]
          for (id <- ids) {
            list.append(CategoryCountInfo(id, 0, 1, 0))
          }
          list
        } else if (userAction.pay_category_ids != "null") {
          //搜索
          val ids: Array[String] = userAction.pay_category_ids.split(",")
          val list: ListBuffer[CategoryCountInfo] = new ListBuffer[CategoryCountInfo]
          for (id <- ids) {
            list.append(CategoryCountInfo(id, 0, 0, 1))
          }
          list
        } else {
          Nil
        }
      }
    }

    //将相同品类的分成一组
    val groupRDD: RDD[(String, Iterable[CategoryCountInfo])] = infoRDD.groupBy(info => info.categoryId)

    //将分组后的数据进行聚合处理: 返回一个元组(品类id, 聚合后的CategoryCountInfo)

    val reduceRDD: RDD[(String, CategoryCountInfo)] = groupRDD.mapValues {
      datas =>
        datas.reduce {
          (info1, info2) => {
            info1.clickCount = info1.clickCount + info2.clickCount
            info1.orderCount = info1.orderCount + info2.orderCount
            info1.payCount = info1.payCount + info2.payCount
            info1
          }
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

//用户访问动作表
case class UserVisitAction(date: String, //用户点击行为的日期
                           user_id: Long, //用户的ID
                           session_id: String, //Session的ID
                           page_id: Long, //某个页面的ID
                           action_time: String, //动作的时间点
                           search_keyword: String, //用户搜索的关键词
                           click_category_id: Long, //某一个商品品类的ID
                           click_product_id: Long, //某一个商品的ID
                           order_category_ids: String, //一次订单中所有品类的ID集合
                           order_product_ids: String, //一次订单中所有商品的ID集合
                           pay_category_ids: String, //一次支付中所有品类的ID集合
                           pay_product_ids: String, //一次支付中所有商品的ID集合
                           city_id: Long) //城市 id

// 输出结果表
case class CategoryCountInfo(categoryId: String, //品类id
                             var clickCount: Long, //点击次数
                             var orderCount: Long, //订单次数
                             var payCount: Long) //支付次数