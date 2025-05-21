package graphxexamples
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
object SimpleGraphsExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Simple Graph").setMaster("local[*]")
    val sc = new SparkContext(conf); // sc object in spark terminal

    println("spark is ready...")

    // creating the vertices
    val vertices: RDD[(VertexId, String)] = sc.parallelize(Seq(
      (1L, "John"),
      (2L, "Steven"),
      (3L, "Lex"),
      (4L, "Neena"),
    ))
    // creating the edges
    val edges: RDD[Edge[String]] = sc.parallelize(Seq(
      Edge(1L,2L,"follow"),
      Edge(2L,3L,"follow"),
      Edge(3L,4L,"friend"),
      Edge(4L,1L,"likes")
    ));

    // now we need to create graph with help of edges and vertices
    val graph = Graph(vertices,edges)

    // print vertices
    graph.vertices.collect().foreach(println)

    // print edges
    graph.edges.collect().foreach(println)

    sc.stop();
  }
}