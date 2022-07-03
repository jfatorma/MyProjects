package tests

import org.scalatest._
import pbd.PaleBlueDot

//A method named cityPopulations that takes three Strings as parameters and returns a Map of Strings to Ints.
// The input Strings  the countries filename, the cities filename, and the name of a country with any mix
// of upper/lower-case letters. This method outputs a Map that maps the city name of every city in the given
// country to that city's population. The city names in your map must have the same case as they do in the cities
// file. To write manageable test cases, scan through the cities file for countries that only have a few cities
// and use those for your test cases.
class Task3 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("Mix of Upper/Lower Case") {
    val countryName: String = "anDoRrA"

    val computedOutput: Map[String,Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, countryName)

    val expectedMap: Map[String,Int] = Map(
      "la massana" -> 7211,
      "les escaldes" -> 15854,
      "ordino" -> 2553,
      "sant julia de loria" -> 8020)

    assert(computedOutput == expectedMap)
  }

  test("Only Upper Case") {
    val countryName: String = "YEMEN"

    val computedOutput: Map[String,Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, countryName)

    val expectedMap: Map[String,Int] = Map(
      "aden" -> 550744,
      "bajil" -> 48217,
      "ibb" -> 234992,
      "jiblah" -> 9627,
      "lahij" -> 23377,
      "sayyan" -> 69402,
      "yarim" -> 33049,
      "zabid" -> 52589
    )

    assert(computedOutput == expectedMap)
  }

  //A method named aboveAverageCities that takes three Strings as parameters and returns a List of Strings.
  // The input Strings the countries filename, the cities filename, and the name of a country with any mix
  // of upper/lower-case letters. This method outputs a List containing all the city names in the given country
  // with a population greater than the average population of cities in that country. The order of the cities in the
  // returned list is undefined. Your tests must accept any list containing the correct cities regardless of order.
  test("Mix Of Upper/Lower Case & Long List Output") {
    val countryName: String = "NePaL"
    //Country Average = 83623.0
    val expectedList: List[String] = List("bharatpur","biratnagar","butwal","dhangadhi","dharan","hetauda",
      "janakpur","kathmandu","lalitpur","mahendranagar","pokhara","birganj")

    val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, countryName)

    assert(computedOutput.sorted == expectedList.sorted)
  }

  test("One List Output/Upper Case") {
    val countryName: String = "ANDORRA"
    //country Average = 8409.5
    val expectedList: List[String] = List("les escaldes")

    val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, countryName)

    assert(computedOutput.sorted == expectedList.sorted)
  }

  test("All Lower Case") {
    val countryName: String = "congo"
    //country Average = 91050.6875
    val expectedList: List[String] = List("brazzaville")

    val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, countryName)

    assert(computedOutput.sorted == expectedList.sorted)
  }

  test("Accepts Equal To Average") {
    val countryName: String = "anguilla"
    //country Average = 1379
    val expectedList: List[String] = List()

    val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, countryName)

    assert(computedOutput.sorted == expectedList.sorted)
  }


}
