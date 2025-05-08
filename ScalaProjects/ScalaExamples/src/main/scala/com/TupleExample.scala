package com

object TupleExample {
  var t1 = (1,"John",32,"Tester")
  def main(args: Array[String]): Unit = {
    println(t1)
    println("id is "+t1._1)
    println("name s "+t1._2)
    println("age is "+t1._3)
    println("Designation is "+t1._4)
  }
}
