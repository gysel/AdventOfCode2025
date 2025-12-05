fun main() {
    val lines = InputData.readLines("day01.txt")
    val instruction = parseInput(lines)
    "Part 1" {
        var position = 50
        var solution = 0
        instruction.forEach { (direction, steps) ->
            position = (position + steps * direction) % 100
            if (position == 0) solution++
        }
        solution shouldBe 1081
    }
    "Part 2" {
        var position = 50
        var solution = 0
        instruction.forEach { (direction, steps) ->
            repeat(steps) {
                position = (position + direction) % 100
                if (position == 0) solution++
            }
        }
        solution shouldBe 6689
    }
}

private fun parseInput(lines: List<String>): List<Pair<Int, Int>> = lines.map { line ->
    val direction = when (line[0]) {
        'L' -> -1
        'R' -> 1
        else -> {
            throw IllegalStateException("Unknown direction $line[0]")
        }
    }
    val steps = (line.drop(1).toInt())
    direction to steps
}
