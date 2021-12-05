fun part1() {

}
fun part2() {

}
data class Coordinates (val x: Int, val y: Int)
data class Line (val start: Coordinates, val end: Coordinates)

fun main () {
    val input = readInput("Day05")
    val lines = input.map {
        val (start, end) = it.split("->")
        val (x1, y1) = start.trim().split(',')
        val (x2, y2) = end.trim().split(',')
        Line(Coordinates(x1.toInt(), y1.toInt()), Coordinates(x2.toInt(), y2.toInt()))
    }

    val verticalOrHorizontalLines = lines
        .filter { it.start.x == it.end.x || it.start.y == it.end.y }
    val maxX = verticalOrHorizontalLines
        .map { listOf(it.start.x, it.end.x) }
        .flatten()
        .maxOf { it }
    val maxY = verticalOrHorizontalLines
        .map { listOf(it.start.y, it.end.y) }
        .flatten()
        .maxOf { it }
    val matrix2D = List<MutableList<Int>>(maxX + 1) { MutableList(maxY + 1) { 0 } }

    fun drawLine (line: Line) {
        // vertical line
        val dx = line.start.x - line.end.x
        val dy = line.start.y - line.end.y
        val rangeX = when {
            dx > 0 -> line.end.x..line.start.x
            dx < 0 -> line.start.x..line.end.x
            else -> 0..0
        }
        val rangeY = when {
            dy > 0 -> line.end.y..line.start.y
            dy < 0 -> line.start.y..line.end.y
            else -> 0..0
        }

        if (line.start.x == line.end.x) {
            for (i in rangeY) {
                matrix2D[line.start.x][i] += 1
            }
        }
        if (line.start.y == line.end.y) {
            for (i in rangeX) {
                matrix2D[i][line.start.y] += 1
            }
        }
    }
    for (line in verticalOrHorizontalLines) {
        drawLine(line)
    }

    println(matrix2D.flatten().filter { it > 1 }.size)
}