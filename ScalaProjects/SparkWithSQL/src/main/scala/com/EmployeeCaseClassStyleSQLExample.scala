package com
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.functions._

case class Employee(id:Long,name:String,department:String,salary:Double)
object EmployeeCaseClassStyleSQLExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._
    //println("spark is ready...")

    val employeeDS:Dataset[info.Employee]=spark.read.option("multiline",true).json("data/employees.json").as[info.Employee]

    employeeDS.createOrReplaceTempView("employees");  // we want to use sql query using employee table

    val result1 = spark.sql("select * from employees");
    result1.show();
    val result2 = spark.sql("select name,salary from employees");
    result2.show();
    val result3 = spark.sql("select * from employees where salary > 5000");
    result3.show();
    val result4 = spark.sql("select * from employees where id=1");
    result4.show();
    spark.stop();
  }
}
