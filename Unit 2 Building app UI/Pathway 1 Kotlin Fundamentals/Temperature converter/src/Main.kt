fun main() {
    val celsiusToFahrenheit: (Double) -> Double = { temperature ->
        9.0 * temperature / 5.0 + 32.0
    }
    val kelvinToCelsius: (Double) -> Double = { temperature ->
        temperature - 273.15
    }
    val fahrenheitToKelvin: (Double) -> Double = { temperature ->
        5.0 * (temperature - 32.0) / 9.0 + 273.15
    }

    printFinalTemperature(27.0, "Celsius", "Fahrenheit", celsiusToFahrenheit)
    printFinalTemperature(350.0, "Kelvin", "Celsius", kelvinToCelsius)
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin", fahrenheitToKelvin)

}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}