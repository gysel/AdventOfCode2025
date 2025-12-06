import InputData.Companion.readLines

fun main() {
    val input = readLines("day06.txt")

    "Part 1" {
        val data: List<List<String>> = input
            .map { line -> line.split(Regex("\\s+")) }
        val operations = data.last()
        val numbers = data.dropLast(1)
            .map { line -> line.map(String::toLong) }
        (0..<numbers.first().size).sumOf { i ->
            val operation: (Long, Long) -> Long = operation(operations[i])
            numbers.map { it[i] }.reduce { acc, l -> operation(acc, l) }
        } shouldBe 6605396225322L
    }

    "Example 2" {
        val example = readLines("example06-2.txt")
        partTwo(example) shouldBe 3263827L
    }

    "Part 2" {
        partTwo(input) shouldBe 11052310600986L
    }
}

private fun partTwo(input: List<String>): Long {
    var operation = operation("*")
    val operations = input.last()
    val numbers = input.dropLast(1)
    val width = input.maxOf { it.length }
    val block = mutableListOf<Long>()
    var result = 0L
    (0..width).forEach { i ->
        operations.getOrNull(i)
            ?.takeIf { it != ' ' }
            ?.also { operation = operation(it.toString()) }
        val currentNumber = numbers
            .mapNotNull { it.getOrNull(i)?.takeIf { c -> c != ' ' } }
            .joinToString("")
        if (currentNumber.isBlank() || i == width) { // end of the block or end of the file
            result += block.reduce(operation)
            block.clear()
        } else {
            block.add(currentNumber.toLong())
        }
    }
    return result
}

private fun operation(char: String): (Long, Long) -> Long {
    val operation: (Long, Long) -> Long = when (char) {
        "*" -> Long::times
        "+" -> Long::plus
        else -> throw IllegalStateException("Unknown operation $char")
    }
    return operation
}
