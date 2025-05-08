package com

object FunctionsExamples {

  object Math {
    def add(a:Int, b:Int):Int = {
      var sum = a+b;
      return sum;
    }
    def sub(a:Int,b:Int):Int = a-b;
    // scala allow to write function name as operator
    def *(a:Int, b:Int ):Int = a*b;
  }
  def main(args: Array[String]): Unit = {
    println(Math.add(10,20));
    println(Math.sub(100,50));
    println(Math.*(2,6));
  }
}
