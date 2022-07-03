package tests

import org.scalatest._
import pbd.PaleBlueDot

//In the tests package, complete the test suite named Task2.
// This test suite will test a method named averagePopulation in the PaleBlueDot object.
// The averagePopulation method will take 3 Strings as parameters which represent, in order,
// the countries filename, the cities filename, and the name of a country with any mix of upper/lower-case letters.
// This method will return the average population of all cities in the input country as a Double

class Task2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("Lower Case") {
    val countryName: String = "nepal"

    val epsilon: Double = 0.00000001
    val countryPopulation: Double = 2926805.0
    val allCities: Double = 35.0
    val expected: Double = 83623.0
    val output: Double = countryPopulation / allCities

    val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, countryName)
    assert(Math.abs(computedOutput - expected) < epsilon)
    assert(Math.abs(output - expected) < epsilon)
  }


  test("Mix of Upper/Lower Case") {
    val countryName: String = "CoNgO"

    val epsilon: Double = 0.00000001
    val countryPopulation: Double = 1456811.0
    val allCities: Double = 16.0
    val expected: Double = 91050.6875
    val output: Double = countryPopulation / allCities

    val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, countryName)
    assert(Math.abs(computedOutput - expected) < epsilon)
    assert(Math.abs(output - expected) < epsilon)
  }

  test("UpperCase") {
    val countryName: String = "ANDORRA"

    val epsilon: Double = 0.00000001
    val countryPopulation: Double = 33638.0
    val allCities: Double = 4.0
    val expected: Double = 8409.5
    val output: Double = countryPopulation / allCities

    val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, countryName)
    assert(Math.abs(computedOutput - expected) < epsilon)
    assert(Math.abs(output - expected) < epsilon)
  }
}
