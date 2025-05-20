package streamexamples
import org.apache.spark.sql.SparkSession
object StructuredStreamWordCountExamples {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").
      appName("Structured Streaming").getOrCreate();

    import spark.implicits._

    val lines = spark.readStream.
      format("socket").
      option("host","localhost").
      option("port",9999)
      .load()

    val query = lines.writeStream.format("console").start()

    query.awaitTermination();

    spark.stop();
  }
}
