name := "scala-spark-example"
lazy val scala213 = "2.13.4"
lazy val scala212 = "2.12.13"
lazy val supportedScalaVersions = List(scala212, scala213)
lazy val sparkVersion = "3.1.1"
organization := "org.prince"
version      := "1.0"
scalaVersion := scala212

lazy val root = (project in file("."))
  .settings(
    crossScalaVersions := supportedScalaVersions,
    publish / skip := true,
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql" % sparkVersion,
      "org.specs" % "specs" % "1.4.3" % Test
    )
  )