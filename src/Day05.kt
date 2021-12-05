import kotlin.math.abs

data class Coordinates (val x: Int, val y: Int) {
    override fun toString() = "($x,$y)"
}
data class Line (val start: Coordinates, val end: Coordinates) {
    val type = when {
        start.x == end.x -> "vertical"
        start.y == end.y -> "horizontal"
        else -> "diagonal"
    }

    fun transform (): Line {
        return when {
            start.x < end.x -> this
            start.x == end.x && start.y < end.y -> this
            else -> Line(end, start)
        }
    }
}

fun verifyHowManyTwoLinesOverlap (lines: List<Line>): Int {
    val listCoordinates = mutableMapOf<String, Int>()
    for (line in lines) {
        when (line.type) {
            "vertical" -> {
                (line.start.y..line.end.y).map {
                    val key = Coordinates(line.start.x, it).toString()
                    listCoordinates.put(key, (listCoordinates[key] ?: 0 ) + 1)
                }
            }
            "horizontal" -> {
                (line.start.x..line.end.x).map {
                    val key = Coordinates(it, line.start.y).toString()
                    listCoordinates.put(key, (listCoordinates[key] ?: 0 ) + 1)
                }
            }
            else -> {
                val dx = line.end.x - line.start.x
                val dy = line.end.y - line.start.y
                val direction = if (dy == 0) 0 else dy / abs(dy)
                (0..dx).map { delta ->
                    val key = Coordinates(line.start.x + delta, line.start.y + direction * delta).toString()
                    listCoordinates.put(key, (listCoordinates[key] ?: 0) + 1)
                }
            }
        }
    }
    return listCoordinates.filter { it.value > 1 }.size
}

fun part1 (lines: List<Line>) {
    val verticalOrHorizontalLines = lines
        .filter { it.start.x == it.end.x || it.start.y == it.end.y }
    println("part1: ${verifyHowManyTwoLinesOverlap(verticalOrHorizontalLines)}")
}
fun part2 (lines: List<Line>) {
    println("part1: ${verifyHowManyTwoLinesOverlap(lines)}")
}

fun main () {
    val input = readInput("Day05")
    val lines = input.map {
        val (start, end) = it.split("->")
        val (x1, y1) = start.trim().split(',')
        val (x2, y2) = end.trim().split(',')
        Line(Coordinates(x1.toInt(), y1.toInt()), Coordinates(x2.toInt(), y2.toInt())).transform()
    }
    part1(lines)
    part2(lines)
}