package streamexamples
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
object DStreamExamples {
  def main(args: Array[String]): Unit = {
    //val spark = SparkSession.builder().appName("DataFrame Join Example").master("local[*]").getOrCreate();
    val config = new SparkConf().setMaster("local[*]").setAppName("DStream Exaples")
    val ssc = new StreamingContext(config,Seconds(10))

    //import spark.implicits._
    println("spark streaming examples ready ")

   val lines = ssc.socketTextStream("localhost",9999)
    val words = lines.flatMap(_.split(" "))
    //println(words)    // all information
    //words.foreachRDD(println)
    val displayData = words.map((_:String)=>_)
    displayData.print()
    val wordsCount = words.map((_,1)).reduceByKey(_+_)
    wordsCount.print(); // display information in tuple format with key as word and count of that words

    ssc.start()
    ssc.awaitTermination()
  }
}
