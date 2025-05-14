import org.apache.spark.sql.{SparkSession,DataFrame}
import org.apache.spark.sql.functions._
object DataFrameJoinedExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._
    val employees: DataFrame = spark.read.option("header", true).option("inferSchema", true).
      csv("data/employees.csv")
    val departments: DataFrame = spark.read.option("header", true).option("inferSchema", true).
      csv("data/departments.csv")
    val joinDataFrame: DataFrame = employees.join(departments,"department_id")
    //joinDataFrame.show();     // show all field from both files
    joinDataFrame.select("name","department_name").show(); // show particular fields
    spark.stop();
  }
}
