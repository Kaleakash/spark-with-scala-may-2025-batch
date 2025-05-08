package com

object SetExamples {
  var mySet1:Set[Int]=Set(1,3,5,6,7,3,6,10,8,4);
  var mySet2:Set[String]=Set("Max","John","Alice");
  def main(args: Array[String]): Unit = {
    println(mySet1)
    println(mySet2)
    //mySet1(0)=100;
    println(mySet1(10))
    println("tail "+mySet1.tail)
    println("head "+mySet1.head)
    println(mySet1.min)
    println(mySet1.max)
    println("Retrieve element one by one ")
    mySet2.foreach(println)
  }
}
