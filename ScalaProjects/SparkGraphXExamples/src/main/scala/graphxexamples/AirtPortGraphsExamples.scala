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

    sc.stop();
  }
}
