package com.atguigu.scala.chapte06

/**
 * @author chujian
 * @create 2021-05-11 7:16
 *         类和特质
 */
object Scala14_TestTrait {
  def main(args: Array[String]): Unit = {
    //    var std1: MyStudent18 = new MyStudent18
    //    std1.run()
    //    std1.speak()

    //特质的动态混入 ,在创建对象的时候，为该对象单独的混入某个特质
    val stdBZ: MyStudent18 with TraitBuyBJP = new MyStudent18 with TraitBuyBJP {
      override def buy(): Unit = {
        println("bangzhang bjp")
      }
    }
    stdBZ.run()
    stdBZ.speak()
    stdBZ.buy()
  }

}

trait TraitA {
  def run(): Unit
}

trait TraitB {
  def speak(): Unit
}

trait TraitBuyBJP {
  def buy(): Unit
}


class Super18 {}
//如果不存在继承关系，混入多个特质
//class MyClass18 extends TraitA with TraitB {}

//如果存在继承关系，同时混入特质
//先继承类，在混入特质
class MyStudent18 extends Super18 with TraitA with TraitB {
  override def run(): Unit = {
    println("jingjing   run")
  }

  override def speak(): Unit = {
    println("jingjing speak")
  }
}