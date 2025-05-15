package com
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
object EmplyeeSchemaDDLStyle {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._

    // Define the Schema
    val schemaRef = StructType(
      Array(
        StructField("id",IntegerType,nullable = true),
        StructField("name",StringType,nullable = true),
        StructField("department",StringType,nullable = true),
        StructField("salary",IntegerType,nullable = true),
      )
    )
    // load the json file and created DataFrame reference
    val employeeDF =
      spark.read.schema(schemaRef).option("multiline",true).json("data/employees.json")
    // register the sql
    employeeDF.createOrReplaceTempView("employees");
    val result1 = spark.sql("select * from employees");
    result1.show();
    spark.stop();
  }
}
