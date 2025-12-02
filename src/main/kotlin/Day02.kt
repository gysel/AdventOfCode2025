fun main() {
    val data: List<Pair<Long, Long>> = InputData.read("day02.txt")
        .split(',')
        .map {
            val (first, last) = it.split('-').map(String::toLong)
            first to last
        }
    solve("Part 1", 23534117921L) {
        iterate(data, ::isInvalidNumber1)
    }
    solve("Part 2", 31755323497) {
        iterate(data, ::isInvalidNumber2)
    }

}

private fun iterate(data: List<Pair<Long, Long>>, func: (Long) -> Long): Long {
    var result = 0L
    data.forEach { (first, last) ->
        (first..last).forEach { number ->
            result += func(number)
        }
    }
    return result
}

private fun isInvalidNumber1(number: Long): Long {
    val string = number.toString()
    if (string.length % 2 == 0) {
        val (left, right) = string.chunked(string.length / 2)
        if (left == right) {
            return number
        }
    }
    return 0L
}

private fun isInvalidNumber2(number: Long): Long {
    val string = number.toString()
    (1..(string.length / 2)).forEach { sequenceLength ->
        val set = string.chunked(sequenceLength)
            .toSet()
        if (set.size == 1) {
            return number
        }
    }
    return 0L
}
