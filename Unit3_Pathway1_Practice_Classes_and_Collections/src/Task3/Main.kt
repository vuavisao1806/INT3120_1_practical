package Task3

enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

/*
Question 1: Can you think of a better way to organize the storage of these events?
Answer: Yes. Using a List or a MutableList to store these events instead of using each variable to store them.

What way can you store all the events in one variable?
(Note: It has to be flexible, as more events may be added.
It also needs to efficiently return the count of the number of the events stored in the variable.)

Question 2: Which class or data type would you use? What is one way to add more events?
Answer: I would use a MutableList class and use its function named Add to add more events.
*/

fun main() {
    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)
    val events = mutableListOf(
        event1, event2, event3, event4, event5, event6
    )
    events.forEach { println(it) }
}