fun main() {
    val input = readInput("Day01")
    val depths = input.map { it.toInt() }

    val lastPossibleIndex = depths.lastIndex - 1
    val depthsTriple = mutableListOf<Int>()

    depths.forEachIndexed  { index, _ ->
        if (index < lastPossibleIndex) {
            depthsTriple.add(depths[index] + depths[index + 1] + depths[index + 2])
        }
    }
    println(depthsTriple)

    val measurementsLargerThanPrevious = depthsTriple
        .filterIndexed { index, value -> if (index == 0) false else value > depthsTriple[index - 1]  }
        .size

    println(measurementsLargerThanPrevious)
}
