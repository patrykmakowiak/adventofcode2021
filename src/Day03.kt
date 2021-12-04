fun part1(input: List<String>) {
    val howManyOnes = mutableMapOf<Int, Int>()

    input.forEach { s ->
        s.forEachIndexed { index, c->
            val value = howManyOnes.getOrElse(index) { 0 }
            howManyOnes[index] = value + Character.getNumericValue(c)
        }
    }

    val halfInputLines = input.size / 2
    val howManyMoreThanHalf = howManyOnes.map { if (it.value > halfInputLines) 1 else 0 }
    val binaryValue = howManyMoreThanHalf.joinToString("")
    val gamma = binaryValue.toInt(2)
    val mask = IntArray(binaryValue.length) { 1 }
        .joinToString("")
        .toInt(2)
    val epsilon = gamma.xor(mask);
    println(gamma * epsilon)
}

fun foo1 (input: List<String>): MutableMap<Int, String> {
    val foo = mutableMapOf<Int, String>()

    input.forEach { s ->
        s.forEachIndexed { index, c->
            val value = foo.getOrElse(index) { "" }
            foo[index] = "$value$c"
        }
    }
    return foo
}

fun part2(input: List<String>) {
    val mutableInput = input.toMutableList()
    val oxygenGeneratorRating = 0
    val co2ScrubberRating = 0

    var test = foo1(mutableInput)

    println(test)

   test.forEach  { s ->
        if (mutableInput.size == 1) {
            println(test.values.joinToString("").toInt(2))
            return
        }
        val indexesToDelete = mutableListOf<Int>()
        var oneCounter = 0
        var zeroCounter = 0
        test[s.key]?.forEach { c -> if (c == '1') oneCounter++ else zeroCounter++ }
        if (oneCounter >= zeroCounter) {
            test[s.key]?.forEachIndexed { index, c -> if (c == '0') indexesToDelete.add(index) }
        } else {
            test[s.key]?.forEachIndexed { index, c -> if (c == '1') indexesToDelete.add(index) }
        }
        println(indexesToDelete)
        indexesToDelete.sortedDescending().forEach {
            println(mutableInput[it])
            mutableInput.removeAt(it)
            test = foo1(mutableInput)
            println(test)
        }
   }
    println(test)
}

// 23 // 1161

// 10 // 3621

fun main() {
    val input = readInput("Day03")

//    part1(input)
    part2(input)
}
