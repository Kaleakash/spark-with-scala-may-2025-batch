import org.apache.spark.sql.{SparkSession,DataFrame}
import org.apache.spark.sql.functions._
object MoviesDataFrameExamples {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Example").master("local[*]").getOrCreate();

    import spark.implicits._
    val moviesDF:DataFrame = spark.read.option("header",true).option("inferSchema",true).
      csv("data/movies.csv")

    moviesDF.show();      // display all data
    moviesDF.printSchema();   // display structure of file with data type
    moviesDF.show(2); // top 2 records
    moviesDF.filter($"genre"==="Drama").show(); // condition with genre
    moviesDF.filter($"year" > 2000).show();   // condition of year
    moviesDF.orderBy(desc("rating")).show();  // order may asc or desc
    
    spark.stop();
  }
}
