package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author chujian
 * @create 2021-05-13 7:51
 *         序列化
 *
 */
object Spark02_TestSerializable {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("Spark01_action_reduce").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3具体业务逻辑


    /**
     *
     * user对象是在foreach算子之外声明的，所以其实是在Driver端声明创建
     * user对象的使用在foreach算子的内部使用，所以其实是在excutor端执行的。
     * 这样就会涉及到user对象从driver端传递到executor端，这个是操作是在网络中传输的
     * 所以就要求user对象必须要序列化。
     *
     * 如果判断是否实现了序列化接口？
     * 在作业job提交之前，其中有一行代码 val clenF =sc.clean(f) 用于进行闭包检查
     * 之所以叫闭包检查，是因为当前函数的内部访问了外部函数的变量，属于闭包的形式
     */

    //匿名函数在scala中就存在闭包
    //当前的代码中匿名函数里面使用了外部的变量
    //spark执行原理中，匿名函数是需要发送给executor端执行
    //但是由于存在闭包，那么就需要判断闭包中是否引用外部的变量
    //因为这个变量是在driver端声明的，所以必须要保证这个变量可以序列化

    //所以spark根本不需要执行逻辑，就应该能够判断出变量对象是否能够序列化
    //spark运行作业之前，会首先执行闭包检测功能



    //创建对象
    val user1 = new User
    user1.name = "chuijan"

    val user2 = new User
    user2.name = "xinshang"


    //创建第一个RDD
    val rdd: RDD[User] = sc.makeRDD(List(user1, user2))
    rdd.foreach(println)

    //关闭连接
    sc.stop()
  }
}

class User extends Serializable{
  var name: String = _

  override def toString = s"User(name=$name)"
}
