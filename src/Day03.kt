import kotlin.math.abs

data class Day03Number(val value: Int, val begin: Int, val end: Int, val y: Int)
data class Day03Symbol(val symbol: Char, val x: Int, val y: Int)

fun main() {
    fun part1(input: List<String>): Int {
        var ret = 0

        var linePosition = 0
        val numberSet = mutableSetOf<Day03Number>()
        val symbolSet = mutableSetOf<Day03Symbol>()

        for (line in input) {
            val newLine = line + '.'
            println(line)
            println(newLine)

            var digit = ""
            var begin = 0
            var digitFlag = false

            newLine.forEachIndexed { index, c ->
                run {
                    // Character is number
                    if (c.isDigit()) {
                        if (!digitFlag) begin = index

                        digitFlag = true
                        digit += c

//                        if (index == line.length - 1) {
//                            val newNumber = Day03Number(digit.toInt(), begin, begin + digit.length - 1, linePosition)
//                            newNumber.println()
//                            numberSet.add(newNumber)
//
//                            digitFlag = false
//                            digit = ""
//                        }
                    } else {
                        if (digitFlag) {
                            val newNumber = Day03Number(digit.toInt(), begin, begin + digit.length - 1, linePosition)
                            newNumber.println()
                            numberSet.add(newNumber)
                        }

                        // Character is symbol
                        if (c != '.') {
                            val newSymbol = Day03Symbol(c, index, linePosition)
//                            newSymbol.println()
                            symbolSet.add(newSymbol)
                        }

                        digitFlag = false
                        digit = ""
                    }

//                    println("$begin, $digit, $digitFlag")
                }
            }

            linePosition++
        }
        numberSet.println()
        symbolSet.println()

        // Find adjacent numbers from numberSet and symbolSet
        for (number in numberSet) {
            val (value, begin, end, y) = number

            for (symbol in symbolSet) {
                val (sv, sx, sy) = symbol

                if ((sy >= y - 1 && sy <= y + 1) && (sx >= begin - 1 && sx <= end + 1)) {
//                if (abs(y - sy) <= 1 && (abs(begin - sx) <= 1 || abs(end - sx) <= 1)) {
                    if (y > 1) println(input[y-1])
                    println(input[y])
                    if (y < input.lastIndex) println(input[y+1])
                    println("$number adjacent with $symbol")
                    ret += value
                    break
                }
            }
        }
        println(ret)

//        val n1 = Day03Number(453, 0, 1, 0)
//        println(n1.isAdjacentSymbol)

        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            println(line)
        }
        println(ret)
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
//    part1(testInput)
//    check(part1(testInput) == 4361)
//    check(part2(testInput) == 2286)

    val input = readInput("Day03")
    part1(input).println()
    // 537732
//    part2(input).println()
}
