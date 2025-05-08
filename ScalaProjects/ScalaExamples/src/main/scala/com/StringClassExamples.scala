package com

object StringClassExamples {
  var msg1:String ="Welcome to Scala Programming"
  def main(args: Array[String]): Unit = {
    println(msg1);
    println(msg1.length())
    println(msg1.toUpperCase())
    println(msg1.toLowerCase())
    println(msg1.contains("Scala"))
  }
}
