package pbd

import java.awt.Desktop
import java.net.URI
import scala.io.{BufferedSource, Source}

object PaleBlueDot {


  /**
   * Task 1
   *
   * Given a country name using a mix of case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename and the countryName input
   * of your method is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName in lowercase letters
   */
  //Implement the method in the PaleBlueDot object named getCountryCode that takes two Strings as parameters
  // and returns a String. The input Strings represent the filename for the countries file,
  // and the name of a country to lookup. This method outputs the country code of the country
  // represented by the input country name. The input country name can have any mix of upper/lower-case letters
  // and the output must be both lowercase letters. If the country is not found in the data file, return the empty String.
  def getCountryCode(countriesFilename: String, countryName: String): String = {
    var countryCode: String = ""
    val lowCaseCountryName: String = countryName.toLowerCase
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    for (line <- countriesFile.getLines()) {
      val splits: Array[String] = line.split("#")
      val nameCountry: String = splits(0).toLowerCase
      val codeCountry: String = splits(1).toLowerCase
      if (nameCountry == lowCaseCountryName) {
        countryCode = codeCountry
      }
    }
    countryCode
  }


  /**
   * Task 2
   *
   * Find the average population of cities in a country
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The average population of cities in the given country
   */
  //In the tests package, complete the test suite named Task2.
  // This test suite will test a method named averagePopulation in the PaleBlueDot object.
  // The averagePopulation method will take 3 Strings as parameters which represent, in order,
  // the countries filename, the cities filename, and the name of a country with any mix of upper/lower-case letters.
  // This method will return the average population of all cities in the input country as a Double
  def averagePopulation(countriesFilename: String, citiesFilename: String, countryName: String): Double = {
    var totalPopulation: Double = 0.0
    var cityNumber: Double = 0.0
    val countryCode: String = getCountryCode(countriesFilename,countryName)
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    for (line <- citiesFile.getLines()) {
      val splits: Array[String] = line.split(",")
      val cityCode: String = splits(0)
      val cityPopulation: String = splits(3)
      if (countryCode == cityCode) {
        cityNumber += 1.0
        totalPopulation += cityPopulation.toDouble
      }
    }
    totalPopulation/cityNumber
  }


  /**
   * Task 3
   */

  /**
   * Returns a Map[cityName -> population] for all cities in the given county. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int. The country name may contain any mix of upper/lower-case letters.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return A Map containing the name and population of every city in the given country
   */
    //A method named cityPopulations that takes three Strings as parameters and returns a Map of Strings to Ints.
    // The input Strings  the countries filename, the cities filename, and the name of a country with any mix
    // of upper/lower-case letters. This method outputs a Map that maps the city name of every city in the given
    // country to that city's population. The city names in your map must have the same case as they do in the cities
    // file. To write manageable test cases, scan through the cities file for countries that only have a few cities
    // and use those for your test cases.
  def cityPopulations(countriesFilename: String, citiesFilename: String, countryName: String): Map[String, Int] = {
      val countryCode: String = getCountryCode(countriesFilename, countryName)
      var cityMap: Map[String,Int] = Map()
      val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
      for (line <- citiesFile.getLines().drop(1)) {
        val splits: Array[String] = line.split(",")
        val cityCode: String = splits(0)
        val cityName: String = splits(1)
        val cityPopulation: Int = splits(3).toInt
        if (countryCode == cityCode) {
          cityMap = cityMap + (cityName -> cityPopulation)
        }
      }
      cityMap
  }


  /**
   * Returns a List of city names in the given county and with above average population for that country
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return All city names in given country with a population > the average populations of cities in that country
   */
  //A method named aboveAverageCities that takes three Strings as parameters and returns a List of Strings.
  // The input Strings the countries filename, the cities filename, and the name of a country with any mix
  // of upper/lower-case letters. This method outputs a List containing all the city names in the given country
  // with a population greater than the average population of cities in that country. The order of the cities in the
  // returned list is undefined. Your tests must accept any list containing the correct cities regardless of order.
  def aboveAverageCities(countriesFilename: String, citiesFilename: String, countryName: String): List[String] = {
    var cityList: List[String] = List()
    val countryCode: String = getCountryCode(countriesFilename, countryName)
    val averagePop: Double = averagePopulation(countriesFilename, citiesFilename, countryName)
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    for (line <- citiesFile.getLines().drop(1)) {
      val splits: Array[String] = line.split(",")
      val cityCode: String = splits(0)
      val cityName: String = splits(1)
      val cityPopulation: Int = splits(3).toInt
      if (countryCode == cityCode & cityPopulation > averagePop) {
        cityList = cityList :+ cityName
      }
    }
    cityList
  }


  /**
   * Application Objective
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * Return the closest city to the given location in terms of greater circle distance which is the shortest distance
   * needed to walk along the surface of the Earth to reach a city.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file (ie. the List should have exactly 3 values to return
   *         a single city
   */
    //In the tests package, complete the test suite named ApplicationObjective.
    //This test suite will test a method in the PaleBlueDot object named closestCity
    //that takes a String and a List of Doubles as parameters and returns a List of Strings.
    //The inputs represent the filename for the cities file and a latitude/longitude location
    //(The list of Doubles should always contain 2 values which are the latitude and longitude
    //of a location on Earth). This method outputs the country code, name, and region of the city
    //closest to the input location by greater circle distance. The upper/lower-case and formatting
    //should match exactly as it appears in the file.
    //Example output format: List("cn", "longjiang", "08")
  def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
    var cityList: List[String] = List()
    var lowDistance: Double = 9999e9
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    for (line <- citiesFile.getLines().drop(1)) {
      val splits: Array[String] = line.split(",")
      val countryCode: String = splits(0)
      val cityName: String = splits(1)
      val regionNum: String = splits(2)
      val latitude: Double = splits(4).toDouble
      val longitude: Double = splits(5).toDouble
      val locationList: List[Double] = List(latitude,longitude)
      val circleDistance: Double = PaleBlueDot.greaterCircle(location, locationList)
      if (circleDistance < lowDistance) {
        lowDistance = circleDistance
        cityList = List(countryCode, cityName, regionNum)
      } else {
        cityList = cityList
      }
    }
    cityList
  }
  def greaterCircle(distanceOne:List[Double], locationTwo:List[Double]): Double = {
    val lat1: Double = distanceOne.head
    val lat2: Double = locationTwo.head
    val lon1: Double = distanceOne.last
    val lon2: Double = locationTwo.last
    val R: Double = 6371e3
    val con1: Double = lat1 * Math.PI/180
    val con2: Double = lat2 * Math.PI/180
    val change1: Double = (lat2 - lat1) * Math.PI/180
    val change2: Double = (lon2 - lon1) * Math.PI/180
    val equation1: Double = Math.sin(change1/2) * Math.sin(change1/2) + Math.cos(con1) * Math.cos(con2) *
      Math.sin(change2/2) * Math.sin(change2/2)
    val equation2: Double = 2 * Math.atan2(Math.sqrt(equation1), Math.sqrt(1 - equation1))
    val equation3: Double = R * equation2
    equation3
  }


  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    //openMap(List(43.002743, -78.7874136))
  }

}
