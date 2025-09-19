package MobileNotifications

fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}


fun printNotificationSummary(numberOfMessages: Int) {
    if (numberOfMessages >= 100) {
        println("Your phone is blowing up! You have 99+ notifications.")
        return
    }
    println("You have $numberOfMessages notifications.")
}