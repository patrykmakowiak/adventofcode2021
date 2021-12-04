const val BINGO = 5
data class Number (val value: String, var checked: Boolean)
data class Board (val value: List<List<Number>>, var finished: Boolean)
data class BoardFinished (val sumUnmarkedNumbers: Int, val numberToFind: Int)

fun calculateSumUnmarkedNumbers (board: Board): Int {
    val sum = board
        .value.map { row ->
            row
                .filter { number -> !number.checked }
                .map { number -> number.value.toInt() }
        }
        .flatten()
        .sum()
    return sum
}

fun verify (numberToFind: Int, bingoBoards: List<Board>, allFinishedBoards: MutableList<BoardFinished>) {
    bingoBoards
        .filter { board -> !board.finished }
        .forEach { board ->
            for (i in 0..4) {
                var howManyCheckedInVertical = 0
                var howManyCheckedInHorizontal = 0
                for (j in 0..4) {
                    if (board.value[j][i].checked) {
                        howManyCheckedInVertical++
                    }
                    if (board.value[i][j].checked) {
                        howManyCheckedInHorizontal++
                    }
                }
                if (howManyCheckedInHorizontal == BINGO || howManyCheckedInVertical == BINGO) {
                    val sum = calculateSumUnmarkedNumbers(board)
                    board.finished = true
                    allFinishedBoards.add(BoardFinished(sum, numberToFind))
                    break
                }
            }
        }
}

fun createBingoBoards (input: List<String>): List<Board> {
    val bingoBoards = input
        .drop(2)
        .windowed(5, 6)
        .map { board ->
            val mappedBoard = board.map { row ->
                row
                    .split(" ")
                    .filter { number -> number.isNotBlank() }
                    .map { number -> Number(number, false) }
            }
            Board(mappedBoard, false)
        }
    return bingoBoards
}

fun checkNumber(numberToFind: String, bingoBoards: List<Board>) {
    bingoBoards.forEach { board ->
        board.value.forEach { row ->
            row.forEach { number ->
                if (numberToFind == number.value) {
                    number.checked = true
                }
            }
        }
    }
}

fun part1(input: List<String>, numbersToFind: List<String>) {
    val bingoBoards = createBingoBoards(input)
    val allFinishedBoards = mutableListOf<BoardFinished>()

    for (numberToFind in numbersToFind) {
        checkNumber(numberToFind, bingoBoards)
        verify(numberToFind.toInt(), bingoBoards, allFinishedBoards)
        if (allFinishedBoards.size > 0) {
            println(allFinishedBoards.first().numberToFind * allFinishedBoards.first().sumUnmarkedNumbers)
            return
        }
    }
}

fun part2(input: List<String>, numbersToFind: List<String>) {
    val bingoBoards = createBingoBoards(input)
    val allFinishedBoards = mutableListOf<BoardFinished>()

    for (numberToFind in numbersToFind) {
        checkNumber(numberToFind, bingoBoards)
        verify(numberToFind.toInt(), bingoBoards, allFinishedBoards)
        if (allFinishedBoards.size == bingoBoards.size) {
            println(allFinishedBoards.last().numberToFind * allFinishedBoards.last().sumUnmarkedNumbers)
            return
        }
    }
}

fun main() {
    val input = readInput("Day04")
    val numbersToFind = input.first().split(",").map { it.trim() }

    part1(input, numbersToFind)
    part2(input, numbersToFind)
}