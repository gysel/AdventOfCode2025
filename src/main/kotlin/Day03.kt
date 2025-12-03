fun main() {
    val lines: List<List<Int>> = InputData.readLines("day03.txt")
        .map { line -> line.map { it.digitToInt() } }
    solve("Part 1", 17405) {
        lines.sumOf { line -> findMaxVoltage(line,2) }
    }
    solve("Part 2", 171990312704598L) {
        lines.sumOf { line -> findMaxVoltage(line, 12) }
    }
}

fun findMaxVoltage(bank: List<Int>, numberOfBatteries: Int): Long {
    val maxVoltage = mutableListOf<Int>()
    var remainingBank = bank
    while (maxVoltage.size < numberOfBatteries) {
        val remainingBatteriesNeeded = numberOfBatteries - maxVoltage.size
        if (remainingBatteriesNeeded == remainingBank.size) {
            maxVoltage.addAll(remainingBank)
        } else {
            val battery = remainingBank.dropLast(remainingBatteriesNeeded - 1).max()!!
            maxVoltage.add(battery)
            val index = remainingBank.indexOf(battery)
            remainingBank = remainingBank.drop(index + 1)
        }
    }
    return maxVoltage.joinToString("").toLong()
}
