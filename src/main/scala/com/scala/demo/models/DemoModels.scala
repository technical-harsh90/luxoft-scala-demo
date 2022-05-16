package com.scala.demo.models

object DemoModels {

  case class Sensor(sensorId: String, humidity: Option[Int])

  case class Statistics(sensorId: String, min: String, avg: String, max: String)

  case class Report(numberOfProcessedFiles: Int, numberOfProcessedMeasurements: Int,
                    numberOfFailedMeasurements: Int, stats: List[Statistics])
}
