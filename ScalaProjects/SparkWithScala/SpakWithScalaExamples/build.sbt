ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .settings(
    name := "SpakWithScalaExamples"
  )
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.2"
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
//libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.2"
//libraryDependencies++=Seq(
//  "org.apache.spark" %% "spark-core" % "3.5.5",
//  "org.apache.spark" %% "spark-sql" % "3.5.5"
//)