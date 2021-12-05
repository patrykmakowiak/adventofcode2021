fun transformBitsFromRowsToColumns (input: List<String>): MutableList<String> {
    val bitsInColumns = MutableList<String>(input.first().length) { "" }
    input.forEach { bits ->
        bits.forEachIndexed { index, bit ->
            bitsInColumns[index] += "$bit"
        }
    }
    return bitsInColumns
}

fun part1(input: List<String>) {
    val sumOfOnes = MutableList(input.first().length) { 0 }

    input.forEach { bits ->
        bits.forEachIndexed { index, bit ->
            sumOfOnes[index] += Character.getNumericValue(bit)
        }
    }
    val halfInputLines = input.size / 2
    val howManyMoreThanHalf = sumOfOnes.map { if (it > halfInputLines) 1 else 0 }
    val binaryValue = howManyMoreThanHalf.joinToString("")
    val gamma = binaryValue.toInt(2)
    val mask = IntArray(binaryValue.length) { 1 }
        .joinToString("")
        .toInt(2)
    val epsilon = gamma.xor(mask);
    println(gamma * epsilon)
}

fun part2(input: List<String>) {
    val mutableInput = input.toMutableList()
    val co2ScrubberRating = 0
    var bitsInColumns = transformBitsFromRowsToColumns(mutableInput)
    val indexesToDelete = mutableListOf<Int>()
    indexesToDelete.contains(1)

    bitsInColumns.forEach  { bits ->
        var oneCounter = 0
        var zeroCounter = 0
        val filteredBits = bits
            .filterIndexed { index, _ -> indexesToDelete.contains(index) }
        filteredBits
            .forEach { bit -> if (bit == '1') oneCounter++ else zeroCounter++ }
        if (oneCounter >= zeroCounter) {
            bits.forEachIndexed { index, bit -> if (bit == '0') indexesToDelete.add(index) }
        } else {
            bits.forEachIndexed { index, bit -> if (bit == '1') indexesToDelete.add(index) }
        }
//        indexesToDelete.sortedDescending().forEach {
//            mutableInput.removeAt(it)
//            bitsInColumns = transformBitsFromRowsToColumns(mutableInput)
//        }
   }
    val oxygenGeneratorRating = bitsInColumns.joinToString("").toInt(2)
    println(oxygenGeneratorRating)
}

fun main() {
    val input = readInput("Day03")
    part1(input)
    part2(input)
    // 4203981
}
