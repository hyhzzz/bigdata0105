package com.atguigu.scala.chapte11

/**
 * @author chujian
 * @create 2021-05-11 19:15
 *         泛型
 */

class Parent {}

class Child extends Parent {}

class SubChild extends Child {}

//泛型模板
//不可变性
//class MyList[T] {}
//协变
//class MyList[+T] {}
//逆变
//class MyList[-T]{}


class Scala01_TestGeneric {
  def main(args: Array[String]): Unit = {
    //    var s: MyList[Child] = new MyList[SubChild]
    //    var s: MyList[Child] = new MyList[Parent]
    //    var s: MyList[Child] = new MyList[SubChild]
  }
}
