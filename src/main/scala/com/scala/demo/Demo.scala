package com.scala.demo

import com.scala.demo.service.DemoService

object Demo {

  def main(args: Array[String]): Unit = {
    val report = new DemoService().processSensors(args.headOption.getOrElse(""))

    println(s"Num of processed files : ${report.numberOfProcessedFiles}")
    println(s"Num of processed measurements : ${report.numberOfProcessedMeasurements}")
    println(s"Num of failed measurements : ${report.numberOfFailedMeasurements}")
    println("\nSensors with highest avg humidity:\n")
    println("sensor-id,min,avg,max")
    report.stats.foreach { stats =>
      println(s"${stats.sensorId},${stats.min},${stats.avg},${stats.max}")
    }
  }

}
