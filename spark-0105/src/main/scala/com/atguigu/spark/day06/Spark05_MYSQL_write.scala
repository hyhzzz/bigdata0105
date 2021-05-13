package com.atguigu.spark.day06

import java.sql.{Connection, DriverManager, PreparedStatement}
import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.{SparkConf, SparkContext, rdd}

/**
 * @author chujian
 * @create 2021-05-13 9:21
 *         向MySQL数据库中写入数据
 *
 */
object Spark05_MYSQL_write {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //数据库连接4要素
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://hadoop202:3306/test"
    val userName = "root"
    val passWd = "123456"

    /*
    //在循环中创建对象，效率低
    rdd.foreach{
      case (name,age)=>{
        //注册驱动
        Class.forName(driver)
        //获取连接
        val conn: Connection = DriverManager.getConnection(url,userName,passWd)
        //执行的sql
        var sql:String = "insert into user(name,age) values(?,?)"
        //获取数据库操作对象
        val ps: PreparedStatement = conn.prepareStatement(sql)
        //给参数赋值
        ps.setString(1,name)
        ps.setInt(2,age)
        //执行sql语句
        ps.executeUpdate()
        //释放资源
        ps.close()
        conn.close()
      }
    }*/

    /*
    //注册驱动
     Class.forName(driver)
     //获取连接
     val conn: Connection = DriverManager.getConnection(url,userName,passWd)
     //执行的sql
     var sql:String = "insert into user(name,age) values(?,?)"
     //获取数据库操作对象
     val ps: PreparedStatement = conn.prepareStatement(sql)

     rdd.foreach{
       case (name,age)=>{
         //给参数赋值
         ps.setString(1,name)
         ps.setInt(2,age)
         //执行sql语句
         ps.executeUpdate()
       }
     }
     //释放资源
     ps.close()
     conn.close()*/
    /*rdd.foreachPartition {
      datas => {
        //注册驱动
        Class.forName(driver)
        //获取连接
        val conn: Connection = DriverManager.getConnection(url, userName, passWd)
        //执行的sql
        var sql: String = "insert into user(name,age) values(?,?)"
        //获取数据库操作对象
        val ps: PreparedStatement = conn.prepareStatement(sql)
        datas.foreach {
          case (name, age) => {
            //给参数赋值
            ps.setString(1, name)
            ps.setInt(2, age)
            //执行sql语句
            ps.executeUpdate()
          }
        }
        //释放资源
        ps.close()
        conn.close()
      }
    }*/

    // 关闭连接
    sc.stop()
  }
}