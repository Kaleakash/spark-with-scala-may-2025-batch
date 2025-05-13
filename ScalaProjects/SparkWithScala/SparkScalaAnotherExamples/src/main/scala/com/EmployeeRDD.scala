package com
import org.apache.spark.sql.SparkSession
object EmployeeRDD {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Spark Example")
      .master("local[*]")
      .getOrCreate()

    // load raw data
    val employeeRawData = spark.sparkContext.textFile("data/employees.csv")
    println("Employee data including heading column")
    employeeRawData.collect().foreach(println)
    // remove header --- 1st transformation
    val header = employeeRawData.first();
    val employeeData = employeeRawData.filter(line=>line != header);

    println("Employee data excluding heading column")
    employeeData.collect().foreach(println)

    spark.stop()
  }
}
