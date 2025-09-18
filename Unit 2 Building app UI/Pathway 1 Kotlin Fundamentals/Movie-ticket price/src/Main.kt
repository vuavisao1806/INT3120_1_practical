fun main() {
    val child = 5
    val adult = 28
    val senior = 87
    var unknown = 101

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, !isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
    println("The movie ticket price for a person aged $unknown is \$${ticketPrice(unknown, isMonday)}.")

    unknown = 0
    println("The movie ticket price for a person aged $unknown is \$${ticketPrice(unknown, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    val cost = when {
        age in 1..12 -> 15
        age in 13..60 && !isMonday -> 30
        age in 13..60 -> 25
        age in 61..100 -> 20
        else -> -1
    }
    return cost
}