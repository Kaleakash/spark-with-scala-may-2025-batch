import org.apache.spark.sql.{SparkSession,DataFrame}
import org.apache.spark.sql.functions._

object CombinedTwoDataFrame {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Example").master("local[*]").getOrCreate();
    import spark.implicits._
    val emp1: DataFrame = spark.read.option("header", true).option("inferSchema", true).
      csv("data/employee1.csv")
    val emp2: DataFrame = spark.read.option("header", true).option("inferSchema", true).
      csv("data/employee2.csv")
    val empCombined:DataFrame = emp1.union(emp2);
    empCombined.show();
    spark.stop();
  }
}
