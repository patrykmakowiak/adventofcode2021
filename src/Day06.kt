fun simulate(fishesPerDayLife: MutableMap<Int, Long>, lastDay: Int) {
    val lastDayIndex = lastDay -1
    for (i in 0..lastDayIndex) {
        val newFishes = fishesPerDayLife[0] ?: 0
        for (j in 0..8) {
            when {
                j == 8 -> fishesPerDayLife[8] = newFishes
                j == 6 -> fishesPerDayLife[j] = (fishesPerDayLife[j+1] ?: 0) + newFishes
                else -> fishesPerDayLife[j] = fishesPerDayLife[j+1] ?: 0
            }
        }
    }
    println(fishesPerDayLife.values.sum())
}

fun part1(input: MutableMap<Int, Long>, lastDay: Int) {
    val fisheshPerDayLife = input.toMutableMap()
    simulate(fisheshPerDayLife, lastDay)
}
fun part2(input: MutableMap<Int, Long>, lastDay: Int) {
    val fisheshPerDayLife = input.toMutableMap()
    simulate(fisheshPerDayLife, lastDay)
}

fun main() {
    val input = readInput("Day06")
    val fishesPerDayLife = mutableMapOf<Int, Long>()
    input
        .map { it.split(",") }
        .map { it.map { it -> it.toInt() } }
        .flatten()
        .forEach { fishesPerDayLife[it] = (fishesPerDayLife[it] ?: 0) + 1 }

    part1(fishesPerDayLife, 80)
    part2(fishesPerDayLife, 256)
}