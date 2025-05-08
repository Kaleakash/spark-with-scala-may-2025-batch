package com

object OptionExamples {
  var myList:List[Int]=List(1,2,3,4,5,6);
  def main(args: Array[String]): Unit = {
    println(myList);
    println(myList.find(_>3))
    println(myList.find(_>39))
  }
}
