/*
* https://adventofcode.com/2023/day/4
* */


fun main() {
    fun splitWinningAndHaveNumbers(lineContent: String): Pair<List<Int>, List<Int>> {
        val winningNumbers = lineContent.split("|")[0].trim().split(" ").filterNot { s -> s == "" }.map { s -> s.toInt() }
        val haveNumbers = lineContent.split("|")[1].trim().split(" ").filterNot { s -> s == "" }.map { s -> s.toInt() }
        return Pair(winningNumbers, haveNumbers)
    }

    fun part1(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            println(line)
            val (lineID, lineContent) = splitLineIDAndContent(line)

            val (winningNumbers, haveNumbers) = splitWinningAndHaveNumbers(lineContent)

            val winningNumbersSet = winningNumbers.toSet()
            val haveNumbersSet = haveNumbers.toSet()
            val setDifference = haveNumbersSet - winningNumbersSet

            val matchNumbers = haveNumbersSet.size - setDifference.size
            val cardPoint = if (matchNumbers == 0) 0 else Math.pow(2.0, matchNumbers - 1.0).toInt()

            cardPoint.println()
            ret += cardPoint
        }
        println(ret)
        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0

        // https://kotlinlang.org/docs/constructing-collections.html#initializer-functions-for-lists
        val cardNumbers = MutableList(input.size) { 1 }

        for (line in input) {
            println(line)
            val (lineID, lineContent) = splitLineIDAndContent(line)

            val (winningNumbers, haveNumbers) = splitWinningAndHaveNumbers(lineContent)

            val winningNumbersSet = winningNumbers.toSet()
            val haveNumbersSet = haveNumbers.toSet()
            val setDifference = haveNumbersSet - winningNumbersSet

            val matchNumbers = haveNumbersSet.size - setDifference.size

            for (i in 0..<matchNumbers) {
                cardNumbers[lineID + i] += cardNumbers[lineID - 1]
            }
            ret = cardNumbers.sum()
            cardNumbers.println()
        }
        println(ret)
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
//    part1(testInput)
    check(part1(testInput) == 13)
//    part2(testInput)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
//    part1(input).println()
    part2(input).println()
}
