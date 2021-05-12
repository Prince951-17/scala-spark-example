package com.prince

import org.apache.spark.SparkContext
import org.apache.spark.sql.{SQLContext, SparkSession}

object SqlContextExample extends App {

  val spark: SparkSession = SparkSession.builder()
    .master("local")
    .appName("SparkByExamples.com")
    .getOrCreate()

  spark.sparkContext.setLogLevel("SqlContextExample")


  val sparkContext: SparkContext = spark.sparkContext
  println(sparkContext)
  val sqlContext: SQLContext = spark.sqlContext
  //read csv with options
  val df = sqlContext.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
    .csv("src/main/resources/zipcodes.csv")
  df.show()
  df.printSchema()

  df.createOrReplaceTempView("TAB")
  sqlContext.sql("select * from TAB")
    .show(false)

}
