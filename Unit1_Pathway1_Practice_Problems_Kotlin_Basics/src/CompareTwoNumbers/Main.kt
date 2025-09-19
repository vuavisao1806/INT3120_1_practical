package CompareTwoNumbers

fun main() {
    println(trackScreenTimeVersusYesterday(300, 250))
    println(trackScreenTimeVersusYesterday(300, 300))
    println(trackScreenTimeVersusYesterday(200, 220))
}

fun trackScreenTimeVersusYesterday(timeSpentToday: Int, timeSpentYesterday: Int): Boolean {
    return (timeSpentToday > timeSpentYesterday)
}