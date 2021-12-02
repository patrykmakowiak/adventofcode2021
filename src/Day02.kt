fun main() {
    val input = readInput("Day02")
    var horizontal = 0
    var depth = 0
    var aim = 0

    input.forEach {
        val (command, value) = it.split(' ')
        val valueNum = value.toInt()
        when (command) {
            "down" -> aim += valueNum
            "up" -> aim -= valueNum
            "forward" -> {
                horizontal += valueNum
                depth += aim * valueNum
            }
        }
    }

    val result = horizontal * depth
    println(result)
}
