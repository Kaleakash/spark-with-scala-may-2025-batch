package mlexamples
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
object SimpleMLExamples {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._
     println("Ml Example ready")
    // label 1 means male and 0 means female
     val data = spark.createDataFrame(Seq(
       ("male",18,1),
       ("female",31,0),
       ("male",19,1),
       ("male",21,1),
       ("female",28,0),
       ("male",25,1),
     )).toDF("gender","age","label")

    val genderIndexer = new StringIndexer().setInputCol("gender").setOutputCol("genderIndexer");
    val assember = new VectorAssembler().setInputCols((Array("genderIndexer","age"))).setOutputCol("features");
    val lr = new LogisticRegression().setMaxIter(10).setRegParam(0.2).setElasticNetParam(0.7);
    val pipeline = new Pipeline().setStages(Array(genderIndexer,assember,lr));
    val model = pipeline.fit(data);
    val predication = model.transform(data);
    predication.show()

    spark.stop();
  }
}
