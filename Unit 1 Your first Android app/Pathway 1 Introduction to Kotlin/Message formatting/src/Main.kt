fun main() {
    val baseSalary = 5000
    val bonusAmount = 1000
    val totalSalary = "$baseSalary + $bonusAmount"
    println("Congratulations for your bonus! You will receive a total of $totalSalary (additional bonus).")
}

/**
 * 1. Can you figure out the output of this code before you run it in Kotlin Playground?
 * - Yes. It's "Congratulations for your bonus! You will receive a total of 5000 + 1000 (additional bonus)."
 * - This is because variable totalSalary = "$baseSalary + $bonusAmount", so I only need to replace $baseSalary, $bonusAmount with their value, 5000 and 1000, respectively.
 *
 * 2. When you run the code in Kotlin Playground, does it print the output that you expected?
 * - Yes.
 */