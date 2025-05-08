package com

object ListExamples {
  val myList1: List[Int] = List(10,20,30,40,50)
  val myList2 :List[String]= List("Lex","Tom","John")
  def main(args: Array[String]): Unit = {
    println(myList1)
    println(myList2)
    println(myList1(0))
    //myList1(0)=100;
    println("mylist1 tail "+myList1.tail)
    println("mylist1 head "+myList1.head)
    println(myList1.isEmpty)
    println(myList1.reverse)
    println("Retrieve element one by one using for each function")
    myList2.foreach( println )

  }
}
