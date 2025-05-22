package graphxexamples
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
object AirtPortGraphsExamples {

  case class Airport(name:String,city:String)
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Graph").setMaster("local[*]")
    val sc = new SparkContext(conf); // sc object in spark terminal

    println("spark is ready...")

    // load vertices files data
    val aiportVertices = sc.textFile("file:///home/labuser/Desktop/spark-with-scala-may-2025-batch/ScalaProjects/SparkGraphXExamples/data/airport.txt").map{
      line =>
        val Array(id,name,city)=line.split(",").map(_.trim)
        (id.toLong,Airport(name,city))
    }

    // load the edges data
    val routesEdges = sc.textFile("file:///home/labuser/Desktop/spark-with-scala-may-2025-batch/ScalaProjects/SparkGraphXExamples/data/routes.txt").map{
      line =>
        val Array(src,dst)=line.split(",").map(_.trim)
        Edge(src.toLong,dst.toLong,"route")
    }

    val airportGraph = Graph(aiportVertices,routesEdges)

    println("All Airports ")
    airportGraph.vertices.collect().foreach{
      case (id,airport)=>
        println(s"$id, ${airport.name} ${airport.city}")
    }

    println("All Route ")
    airportGraph.edges.collect().foreach {
      e=>
        println(s"${e.srcId}  ---> ${e.dstId}")
    }

    println("All Routes with Airport Details:")
    airportGraph.triplets.collect().foreach { triplet =>
      val srcAirport = triplet.srcAttr
      val dstAirport = triplet.dstAttr
      println(s"${triplet.srcId} (${srcAirport.name}, ${srcAirport.city}) --> ${triplet.dstId} (${dstAirport.name}, ${dstAirport.city})")
    }

    println("InDegree (Incoming Routes Count per Airport):")
    airportGraph.inDegrees.collect().foreach {
      case (id, count) =>
        println(s"$id has $count incoming routes")
    }

    println("OutDegree (Outgoing Routes Count per Airport):")
    airportGraph.outDegrees.collect().foreach {
      case (id, count) =>
        println(s"$id has $count outgoing routes")
    }
    val maxIncoming = airportGraph.inDegrees.max()(Ordering.by(_._2))
    val maxOutgoing = airportGraph.outDegrees.max()(Ordering.by(_._2))

    println(s"Airport with most incoming routes: ${maxIncoming._1}, count: ${maxIncoming._2}")
    println(s"Airport with most outgoing routes: ${maxOutgoing._1}, count: ${maxOutgoing._2}")


    val airportMap = aiportVertices.collectAsMap()
    println(s"Most Incoming Airport: ${airportMap(maxIncoming._1)}")
    println(s"Most Outgoing Airport: ${airportMap(maxOutgoing._1)}")

    sc.stop();
  }
}
