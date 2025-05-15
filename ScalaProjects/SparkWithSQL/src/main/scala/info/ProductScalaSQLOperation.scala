package info
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

import java.util.Properties
object ProductScalaSQLOperation {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._
    // JDBC properties
    val url = "jdbc:mysql://localhost:3306/spark_db"
    val connProperties = new Properties();
    connProperties.put("driver","com.mysql.cj.jdbc.Driver");
    connProperties.put("user","root")
    connProperties.put("password","Welcome@123")
    // read or write data to MySQL
      // 1st parameter url, 2nd parameter table , 3rd parameter properties ref which hold database details.
    val productData:DataFrame = spark.read.jdbc(url,"product",connProperties)
    // Show all data from database
    println("Product details ")
    productData.show();
    spark.stop();
  }
}
