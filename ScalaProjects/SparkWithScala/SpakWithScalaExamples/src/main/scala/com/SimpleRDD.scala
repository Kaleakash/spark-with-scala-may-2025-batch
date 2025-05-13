package com
import org.apache.spark.{SparkContext,SparkConf}
object SimpleRDD {
  def main(args: Array[String]): Unit = {
  //println("Welcome to Spark App!")
    // creating spark configuration and context object
    val conf = new SparkConf().setAppName("Simple RDD Example").setMaster("local[*]")
    val sc = new SparkContext(conf);  // sc object in spark terminal

    // creating RDD reference using list, array, set or map or external file
    val numbers = List(1,2,3,4,5,6,7,8,9,10);
    val numberRDD = sc.parallelize(numbers);
    // if we need we can do some transformation using map, filter, flatMap etc

    // actions
    numberRDD.collect().foreach(println)
  }
}
