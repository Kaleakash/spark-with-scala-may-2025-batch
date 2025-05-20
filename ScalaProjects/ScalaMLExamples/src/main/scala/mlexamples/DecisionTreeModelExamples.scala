package mlexamples
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{DecisionTreeClassifier, LogisticRegression}
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.functions._
object DecisionTreeModelExamples {
  def main(args: Array[String]): Unit = {
    // spark session ready
    val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    import spark.implicits._
    println("Decision  ML Example ready")

  // training Data
    val trainingData = Seq(
      (750,50000,1),
      (550,50000,0),
      (850,50000,1),
      (950,50000,1),
      (250,50000,0),
      (650,50000,0),
    ).toDF("creditScore","income","label")


    val assmeber = new VectorAssembler()
      .setInputCols(Array("creditScore","income"))
      .setOutputCol("features")

    val featuredTraining = assmeber.transform(trainingData)

    // Train the Decision Tree Classifier
    val classifier = new DecisionTreeClassifier()
      .setLabelCol("label")
      . setFeaturesCol("features")

    val model = classifier.fit(featuredTraining)
  // load new data from any external files if need
    val newApplicants = Seq(
      (690,45000),
      (610,35000),
      (740,43000),
      (900,55000),
      (860,48000),
      (330,68000),
      (550,35000),
      (700,10000),
      (980,46000),
    ).toDF("creditScore","income")

    val newFeatures = assmeber.transform(newApplicants)

    val predication = model.transform(newFeatures)

    // apply custom business rule to approved or rejected

    val findalDecision = predication.withColumn("finalDecision",
      when($"income" < 45000,lit(0)).otherwise($"prediction")
    )

    val findalDecisionString = findalDecision.withColumn("finalDecisionStatus",
      when(col("finalDecision")===1, "Approved").otherwise("Rejected")
    )


    findalDecisionString.show();
    spark.stop();
  }
}
