package com.scala.demo.utils

import com.scala.demo.models.DemoModels.{Report, Sensor, Statistics}

object Utility {

  def doSensorExist(humidity: Option[String]): Option[Int] = {
    if (humidity.getOrElse("").equalsIgnoreCase("NaN")) None else Some(humidity.get.toInt)
  }

  def generateStats(sensorsByDir: Map[String, List[Sensor]]): List[Statistics] = {
    val groupedSensorsById = sensorsByDir.values.toList.flatten.groupBy(_.sensorId)
    groupedSensorsById.map { case(sensorId, sensors) =>
      val validSensors = sensors.filter(sensor => sensor.sensorId.equals(sensorId) && sensor.humidity.nonEmpty)
      Statistics(
        sensorId,
        findMinimum(validSensors),
        findAverage(validSensors),
        findMaximum(validSensors)
      )
    }.toList
  }

  val findMinimum: List[Sensor] => String = (sensors: List[Sensor]) => if (sensors.isEmpty) "NaN" else sensors.map(_.humidity.get).min.toString
  val findMaximum: List[Sensor] => String = (sensors: List[Sensor]) => if (sensors.isEmpty) "NaN" else sensors.map(_.humidity.get).max.toString
  val findAverage: List[Sensor] => String = (sensors: List[Sensor]) => if (sensors.isEmpty) "NaN" else (sensors.map(_.humidity.get).sum / sensors.length).toString
}
