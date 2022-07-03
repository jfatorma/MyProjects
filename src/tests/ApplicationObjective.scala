package tests

import org.scalatest._
import pbd.PaleBlueDot

//In the tests package, complete the test suite named ApplicationObjective.
//This test suite will test a method in the PaleBlueDot object named closestCity
//that takes a String and a List of Doubles as parameters and returns a List of Strings.
//The inputs represent the filename for the cities file and a latitude/longitude location
//(The list of Doubles should always contain 2 values which are the latitude and longitude
//of a location on Earth). This method outputs the country code, name, and region of the city
//closest to the input location by greater circle distance. The upper/lower-case and formatting
//should match exactly as it appears in the file.
//Example output format: List("cn", "longjiang", "08")
class ApplicationObjective extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("Country->Andorra"){
    val location: List[Double] = List(42.5,1.5333333)

    val expectedOutput: List[String] = List("ad","les escaldes","08")

    val computedOutput: List[String] = PaleBlueDot.closestCity(citiesFilename, location)

    assert(computedOutput.sorted == expectedOutput.sorted)
  }

  test("Country->Congo"){
    val location: List[Double] = List(-4.1536111,13.55)

    val expectedOutput: List[String] = List("cg","madingou","01")

    val computedOutput: List[String] = PaleBlueDot.closestCity(citiesFilename, location)

    assert(computedOutput.sorted == expectedOutput.sorted)
  }

  test("Country->United States"){
    val location: List[Double] = List(29.5633333,-95.2858333)

    val expectedOutput: List[String] = List("us","pearland","TX")

    val computedOutput: List[String] = PaleBlueDot.closestCity(citiesFilename, location)

    assert(computedOutput.sorted == expectedOutput.sorted)
  }

  test("Works_Near_A_Pole1"){
    val location: List[Double] = List(-90.0,180.0)

    val expectedOutput: List[String] = List("ar","ushuaia","23")

    val computedOutput: List[String] = PaleBlueDot.closestCity(citiesFilename, location)

    assert(computedOutput.sorted == expectedOutput.sorted)
  }

  test("Works_Near_A_Pole2"){
    val location: List[Double] = List(90.0,0.0)

    val expectedOutput: List[String] = List("sj","ny-alesund","00")

    val computedOutput: List[String] = PaleBlueDot.closestCity(citiesFilename, location)

    assert(computedOutput.sorted == expectedOutput.sorted)
  }


}