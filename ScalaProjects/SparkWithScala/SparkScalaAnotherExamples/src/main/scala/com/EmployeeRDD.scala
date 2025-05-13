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

    // convert each line or row to tuple using Scala.
    val employeeRDD = employeeData.map{ line =>
      val field = line.split(",")
      val id = field(0).toInt
      val name = field(1)
      val department = field(3)
      val salary = field(2).toDouble
      (id,name,department,salary)
    }
    // display all employee details using collection as tuple
    println("display employee details as each record as tuple")
    employeeRDD.collect().foreach(println)

    // filter the data using department
    val ITEmployee = employeeRDD.filter{case (_,_,department,_)=>department=="IT"}
    println("IT Department employees")
    ITEmployee.collect().foreach(println)


    // filter the data using salary with condition
    val highestSalary = employeeRDD.filter { case (_, _, _,salary) => salary > 40000 }
    println("highest Salary employees")
    highestSalary.collect().foreach(println)

    // department wise salary
    val departmentWiseSalary =
      employeeRDD.map{case (_, _, department, salary) =>(department,salary)}.reduceByKey(_+_)
    //departmentWiseSalary.collect().foreach(println)
    departmentWiseSalary.collect().foreach{
      case (department,total)=>
        println(s"${department} -> ${total}")
    }
    spark.stop()
  }
}
