package streamexamples


import org.apache.spark.sql.{Encoders, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger

case class Product(product:String, amount:Long)

object StructuredStreamFileSystem {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").
      appName("Structured File system Streaming").getOrCreate();

    import spark.implicits._
// rules for schema to match pname and price
    val saleSchema = Encoders.product[Product].schema
    val salesStream  = spark.readStream.
      option("header","true").
      schema(saleSchema).
      csv("data")

    val saleDS = salesStream.as[Product];

    // transformation
    val totalSales = saleDS.groupBy($"product").agg(sum($"amount").as("total_amount"));

    val query = totalSales.writeStream.outputMode("complete").format("console").
      trigger(Trigger.ProcessingTime("20 seconds")).start()

    query.awaitTermination();



  }
}
