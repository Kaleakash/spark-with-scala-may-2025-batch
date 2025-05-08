package com

object MapExamples {
  val myMap:Map[Int,String]=Map(100->"Max",103->"John",102->"Steven",100->"Neena",104->"John")
  def main(args: Array[String]): Unit = {
  println(myMap)
    println(myMap(100))
    //println(myMap(1000))
    println(myMap.keys)
    println(myMap.values)
    println("Retrieve information from map one by one")
    myMap.keys.foreach{key=>
        println("key "+key+" value "+myMap(key))
    }
  }
}
