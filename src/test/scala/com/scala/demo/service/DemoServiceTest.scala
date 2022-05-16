package com.scala.demo.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class DemoServiceTest extends AnyFlatSpec with should.Matchers {

  val demoService: DemoService = new DemoService

  "Scala-Demo" should "calculate sensors metrics for scenario one" in {
    val result = demoService.processSensors("src/test/resources/scenario.one")
    assert(result.numberOfProcessedFiles == 1)
    assert(result.numberOfProcessedMeasurements == 3)
    assert(result.numberOfFailedMeasurements == 1)
  }

  "Scala-Demo" should "calculate sensors metrics for scenario two" in {
    val result = demoService.processSensors("src/test/resources/scenario.two")
    assert(result.numberOfProcessedFiles == 1)
    assert(result.numberOfProcessedMeasurements == 4)
    assert(result.numberOfFailedMeasurements == 1)
  }

  "Scala-Demo" should "calculate sensors metrics for directory with two CSV files" in {
    val result = demoService.processSensors("src/test/resources/scenario.three")
    assert(result.numberOfProcessedFiles == 2)
    assert(result.numberOfProcessedMeasurements == 7)
    assert(result.numberOfFailedMeasurements == 2)
    assert(result.stats.length == 3)
    assert(result.stats.find(_.sensorId.equals("s1")).get.min == "10")
    assert(result.stats.find(_.sensorId.equals("s1")).get.max == "98")
    assert(result.stats.find(_.sensorId.equals("s1")).get.avg == "54")
  }


}
