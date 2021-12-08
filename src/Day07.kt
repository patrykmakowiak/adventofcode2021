import kotlin.math.abs

data class Position (val horizontalPosition: Int, val fuel: Int)

fun calculateNonConstantFuel(steps: Int): Int {
    var fuel = 0
    var howManySteps = steps
    while (howManySteps > 0) {
        fuel += howManySteps
        howManySteps--
    }
    return fuel
}

fun part1(min: Int, max: Int, positions: List<Int>) {
    val positionsWithFuel = mutableListOf<Position>()
    for (i in min..max) {
        val fuel = positions.map { abs(it - i) }
        positionsWithFuel.add(Position(i, fuel.sum()))
    }
    println(positionsWithFuel.minOf { it.fuel })
}

fun part2(min: Int, max: Int, positions: List<Int>) {
    val positionsWithFuel = mutableListOf<Position>()
    for (i in min..max) {
        val fuel = positions.map { calculateNonConstantFuel(abs(it - i)) }
        positionsWithFuel.add(Position(i, fuel.sum()))
    }
    println(positionsWithFuel.minOf { it.fuel })
}

fun main() {
    val input = readInput("Day07")
    val positions = input
        .map { it.split(",") }
        .flatten()
        .map { it.toInt() }


    val min = positions.minOf { it }
    val max = positions.maxOf { it }

    part1(min, max, positions)
    part2(min, max, positions)
}