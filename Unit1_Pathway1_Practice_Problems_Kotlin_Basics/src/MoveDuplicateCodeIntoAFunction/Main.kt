package MoveDuplicateCodeIntoAFunction

fun main() {
    displayWeatherDetails("Ankara", 27, 31, 82)

    displayWeatherDetails("Tokyo", 32, 36, 10)

    displayWeatherDetails("Cape Town", 59, 64, 2)

    displayWeatherDetails("Guatemala City", 50, 55, 7)
}

fun displayWeatherDetails(city: String,
                          lowTemperature: Int, highTemperature: Int,
                          chanceOfRain: Int) {
    println("City: $city")
    println("Low temperature: $lowTemperature, High temperature: $highTemperature")
    println("Chance of rain: $chanceOfRain%")
    println()
}
