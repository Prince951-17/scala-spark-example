package com.prince

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object RDDCache extends App {
  case class ZipCode(recordNumber: Int, zipCode: String, city: String, state: String)

  val spark: SparkSession = SparkSession.builder()
    .master("local")
    .appName("RDDCache")
    .getOrCreate()

  val sc: SparkContext = spark.sparkContext

  val rdd: RDD[String] = sc.textFile("src/main/resources/zipcodes-noheader.csv")

  val rdd2: RDD[ZipCode] = rdd.map(row => {
    val strArray = row.split(",")
    ZipCode(strArray(0).toInt, strArray(1), strArray(3), strArray(4))
  })

  rdd2.persist()


  println(rdd2.count())
}
