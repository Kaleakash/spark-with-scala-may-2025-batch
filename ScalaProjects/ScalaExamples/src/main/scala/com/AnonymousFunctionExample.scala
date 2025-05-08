package com

object AnonymousFunctionExample {
  def info():Unit = {
    println("Welcome")
  }
  // arrow function return value without return keyword if we write one line code
  //multi line code you need to write curly braces.
  val sayHello= ()=>println("Welcome to Arrow/Lambda function syntax")
  val add = (a:Int,b:Int)=>a+b;
  val sub = (x:Int,y:Int)=>x-y
  def main(args: Array[String]): Unit = {
    info();
    sayHello()
    println("Sum of two number is using arrow style "+add(10,20));;
    println("Sub of two number is using arrow style "+sub(100,50));
  }
}
