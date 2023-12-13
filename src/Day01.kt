fun addFirstDigitAndLastDigit(line: String): Int {
    var digits = ""
    for (c in line) {
        if (c.isDigit()) {
            digits += c
        }
    }
    val result: Int = 10 * (digits[0] - '0') + (digits[digits.length - 1] - '0')
    println(result)
    return result
}

fun main() {
    fun part1(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            println(line)
            ret += addFirstDigitAndLastDigit(line)
        }
        println(ret)
        return ret
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    part1(testInput)
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
//    part2(input).println()
}
