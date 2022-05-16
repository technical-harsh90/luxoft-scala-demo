package com.scala.demo.service

import com.scala.demo.models.DemoModels.{Report, Sensor, Statistics}
import com.scala.demo.utils.Utility._

import java.io.{File, FileNotFoundException}
import scala.io.Source

class DemoService {

  def processSensors(dirPath: String): Report = {
    try {
      val files = new File(dirPath).listFiles()

      val sensorsByDir = files.foldLeft(Map.empty[String, List[Sensor]]) { (sensorsList, file) =>
        val contentOfFile = Source.fromFile(file)
        val sensors = contentOfFile.getLines().toList.tail map { record =>
          val (sensorId, humidity) = (record.split(",").headOption.getOrElse(""), doSensorExist(record.split(",").lastOption))
          Sensor(sensorId, humidity)
        }
        sensorsList ++ Map(file.getName -> sensors)
      }

      val numberOfProcessedFiles = files.length
      val numberOfProcessedMeasurements = sensorsByDir.foldLeft(0)(_ + _._2.length)
      val numberOfFailedMeasurements = sensorsByDir.foldLeft(0)(_ + _._2.count(_.humidity.isEmpty))

      Report(numberOfProcessedFiles, numberOfProcessedMeasurements, numberOfFailedMeasurements, generateStats(sensorsByDir))

    } catch {
      case exception: NullPointerException => throw new FileNotFoundException("Invalid directory path provided")
    }
  }


}
