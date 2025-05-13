package com
//import org.apache.spark.sql.SparkSession;
import org.apache.spark.{SparkContext,SparkConf}
object EmployeeRDD {
  def main(args: Array[String]): Unit = {
    // creating spark configuration and context object
    val conf = new SparkConf().setAppName("Simple RDD Example").setMaster("local[*]")
    val sc = new SparkContext(conf); // sc object in spark terminal

    // read the data from employee.csv file
    val employeeRawData = sc.textFile("data/employees.csv");
    //textData.show();
  // transformation
    val header = employeeRawData.first();
    //println(header)
    //val employeeData = employeeRawData.filter(line => line!= header)


    // action


    employeeData.collect().foreach(println)
    sc.stop();
  }

}
