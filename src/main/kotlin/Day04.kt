fun main() {
    "Example 1" {
        val grid = loadInput("example04-1.txt")
        grid.findMoveableRolls().size shouldBe 13
    }

    val grid = loadInput("day04.txt")
    "Part 1" {
        grid.findMoveableRolls().size shouldBe 1416
    }

    "Part 2" {
        val newGrid = grid.toMutableMap()
        var rollsRemoved = 0
        do {
            val rolls = newGrid.findMoveableRolls()
            rolls.forEach { (coordinates, _) -> newGrid[coordinates] = '.' }
            rollsRemoved += rolls.size
        } while (rolls.isNotEmpty())
        rollsRemoved shouldBe 9086
    }
}

private fun loadInput(filename: String): Map<Coordinates, Char> {
    val lines = InputData.readLines(filename)
    return parseGrid(lines)
}

private fun parseGrid(lines: List<String>): Map<Coordinates, Char> {
    val grid = mutableMapOf<Coordinates, Char>()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            grid[Coordinates(x, y)] = c
        }
    }
    return grid
}

private fun Map<Coordinates, Char>.findMoveableRolls() = filter { (coordinates, char) ->
    (char == '@') && (coordinates.neighbours().count { (this[it] ?: '.') == '@' } < 4)
}

data class Coordinates(val x: Int, val y: Int) {
    fun neighbours() = listOf(
        Coordinates(x + 1, y),
        Coordinates(x + 1, y + 1),
        Coordinates(x, y + 1),
        Coordinates(x - 1, y + 1),
        Coordinates(x - 1, y),
        Coordinates(x - 1, y - 1),
        Coordinates(x, y - 1),
        Coordinates(x + 1, y - 1),
    )
}