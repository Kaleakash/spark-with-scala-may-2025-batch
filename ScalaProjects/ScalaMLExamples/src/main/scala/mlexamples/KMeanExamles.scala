package mlexamples
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
object KMeanExamles {
  def main(args: Array[String]): Unit = {
    // spark session ready
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._
    println("K Mean ML Example ready")

  // create the Data Frame age, income
    val data = Seq(
      (25,30000),
      (32,35000),
      (45,40000),
      (30,48000),
      (42,55000),
      (48,46000)
    ).toDF("age","income");
    // combine age and income into features vector
    val assember = new VectorAssembler().setInputCols(Array("age","income")).setOutputCol("features")
    val assemberData = assember.transform(data);
    // K mean with n number of cluster
    val kmeans = new KMeans().setK(5).setSeed(1L).setFeaturesCol("features").setPredictionCol("cluster")
    // train the K mean model
    val model = kmeans.fit(assemberData);
    // apply model to make predication
    val predication = model.transform(assemberData)
  // show result
    predication.show();
    spark.stop();
  }
}
