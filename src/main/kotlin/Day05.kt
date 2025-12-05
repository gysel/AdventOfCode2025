import kotlin.math.max
import kotlin.math.min

fun main() {
    val lines = InputData.readLines("day05.txt")
    val fresh: List<LongRange> = lines.takeWhile { it.isNotBlank() }.map { line ->
        val (from, to) = line.split("-").map(String::toLong)
        from..to
    }
    val available: List<Long> = lines.drop(fresh.size + 1).map(String::toLong)

    "Part 1" {
        available.count { food -> fresh.any { food in it } } shouldBe 638
    }
    "Part 2" {
        countFreshFoods(fresh) shouldBe 352946349407338
    }
}

private fun countFreshFoods(fresh: List<LongRange>): Long {
    val processed = mutableListOf<LongRange>()
    val toProcess = fresh.sortedBy { it.first }.toMutableList()
    while (toProcess.isNotEmpty()) {
        val a = toProcess.removeFirst()
        val b = toProcess.removeFirst()
        if (a overlapsWith b) {
            toProcess.addFirst(a mergeWith b)
        } else {
            processed.add(a)
            toProcess.addFirst(b)
        }
        if (toProcess.size == 1) {
            processed.add(toProcess.removeFirst())
        }
    }
    return processed.sumOf { it.rangeCount() }
}

/**
 * Iterable.count uses a for loop incrementing an int... 🤯
 * This is a "slightly" faster way to do it
 */
private fun LongRange.rangeCount(): Long {
    return this.last - this.first + 1
}

private infix fun LongRange.mergeWith(other: LongRange): LongRange {
    return min(this.first, other.first)..max(last, other.last)
}

private infix fun LongRange.overlapsWith(other: LongRange): Boolean {
    return this.last >= other.first
}
