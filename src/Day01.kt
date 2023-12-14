
fun main() {
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

    fun addFirstDigitAndLastDigitWithSpellOut(line: String): Int {
        val letters = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        var digits = ""
        var currentCharIndex = 0
        while (currentCharIndex < line.length) {
            letters.forEachIndexed { index, s ->
                run {
                    if (currentCharIndex + s.length <= line.length &&
                        line.substring(currentCharIndex, currentCharIndex + s.length) == s
                    ) {
                        val newChar = "${1 + index}"
                        digits += newChar
                    }
                }
            }
            if (line[currentCharIndex].isDigit()) {
                digits += line[currentCharIndex]
            }
            currentCharIndex += 1
        }
        val result: Int = 10 * (digits[0] - '0') + (digits[digits.length - 1] - '0')
        println(result)
        return result
    }

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
        var ret = 0
        for (line in input) {
            println(line)
            ret += addFirstDigitAndLastDigitWithSpellOut(line)
        }
        println(ret)
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
//    part2(testInput)
    check(part2(testInput) == 281)

    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}
