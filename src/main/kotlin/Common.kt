import kotlinx.coroutines.runBlocking

class InputData {
    companion object {
        fun readLines(filename: String): List<String> {
            return this::class.java.classLoader.getResourceAsStream(filename)
                ?.bufferedReader()
                ?.readLines()
                ?: throw IllegalStateException("Unable to read $filename!")
        }

        fun read(filename: String): String {
            return this::class.java.classLoader.getResourceAsStream(filename)
                ?.reader()
                ?.readText()
                ?: throw IllegalStateException("Unable to read $filename!")
        }
    }
}

infix operator fun String.invoke(block: () -> Any) {
    println("\n---- $this ----")
    val start = System.currentTimeMillis()
    val solution = block()
    val millis = System.currentTimeMillis() - start
    val seconds = millis / 1000
    val duration = if (seconds > 0) "${seconds}s ${millis % 1000}ms" else "${millis}ms"
    println("Solution: $solution")
    println("Time:     $duration")
    val reset = "\u001b[0m"
    print(reset)
}

infix fun Any.shouldBe(expected: Any): Any {
    // see https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
    val red = "\u001b[31m"
    val green = "\u001b[32m"
    val fireworks = "\uD83C\uDF89"
    val exclamation = "‼\uFE0F"
    if (this != expected) {
        println("$exclamation${red} Calculation failed $exclamation")
        println("Expected: $expected")
    } else {
        println("$fireworks${green} Calculation correct $fireworks")
    }
    return this
}