package com

object ArrayExamples {
  var myArray:Array[Int]=Array(10,20,30,40);  // initialization of array
  var myArray1:Array[Int]=new Array[Int](2); // memory creation
  def main(args: Array[String]): Unit = {
    println("Size of array "+myArray.length)
    println("Size of array "+myArray1.length)
    myArray1(0)=100;
    myArray1(1)=200;
    //myArray1(2)=300;
    println(myArray(0))
    println(myArray(1))
    println(myArray(2))
    println(myArray)
    println(myArray.mkString(" "))
    println("Element from array using loop")
    for(x<-myArray){
      println(x)
    }
    println("Few Array method")
    println(myArray.max)
    println(myArray.min)
    println(myArray.sum)
  }
}
