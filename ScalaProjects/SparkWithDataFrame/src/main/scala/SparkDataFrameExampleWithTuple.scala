import org.apache.spark.sql.{SparkSession,DataFrame}
//import org.apache.spark.sql.types._
//import org.apache.spark.sql.Row
object SparkDataFrameExampleWithTuple {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Example").master("local[*]").getOrCreate();
    // List of tuples
    val employeeData = List(
      (1,"Steven",34000,"IT"),
      (2,"John",39000,"Marketing"),
      (3,"Lex",38000,"HR"),
      (4,"Neena",40000,"IT"),
      (5,"Veena",49000,"HR"),
      (6,"Leena",43000,"IT"),
    )
    import spark.implicits._
    // create RDD from List
    val employeeRDD = spark.sparkContext.parallelize(employeeData);
    // converting List or tuple or text file or any container to DataFrame
    val df:DataFrame = employeeRDD.toDF("id","name","salary","dept")
    println("Display Employee details as DataFrame")
    df.show();
    println("Display Employee Dataframe Schema")
    df.printSchema()
    println("filter the data using salary")
    df.filter($"salary">35000).show();
    println("filter the data using id")
    df.filter($"id" === 2).show();
    // action
    //employeeRDD.collect().foreach(println)
    println("number of people in each group")
    df.groupBy("dept").count().show();
    println("sum of salary by department")
    df.groupBy("dept").sum("salary").show();
    spark.stop();
  }
}
