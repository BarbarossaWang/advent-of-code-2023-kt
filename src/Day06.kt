import kotlin.math.ceil
import kotlin.math.sqrt

fun main() {
    fun parseTime(line: String): List<Int> {
        val timeList = line.split(":")[1]
            .trim()
            .split(" ")
            .map { it.trim() }
            .filterNot { it == "" }
            .map { it.toInt() }
        return timeList
    }
    fun parseDistance(line: String): List<Int> {
        val distanceList = line.split(":")[1]
            .trim()
            .split(" ")
            .map { it.trim() }
            .filterNot { it == "" }
            .map { it.toInt() }
        return distanceList
    }

    fun parsePartTwo(line: String): String {
        val result = line.split(":")[1]
            .trim()
            .split(" ")
            .map { it.trim() }
            .filterNot { it == "" }
            .reduce { acc, s -> acc + s }
        return result
    }

    fun part1(input: List<String>): Int {
        var ret = 0
        val timeList = parseTime(input[0])
        val distanceList = parseDistance(input[1])

        val totalWin = mutableListOf<Int>()
        for ((i, time) in timeList.withIndex()) {
            var raceWinNumber = 0
//            println("--------------------------")
            for (t in 0..time) {
                if (t * (time - t) > distanceList[i]) {
//                    println("Add $t for ${distanceList[i]}")
                    raceWinNumber++
                }
            }
            totalWin.add(raceWinNumber)
        }

        timeList.println()
        distanceList.println()
        totalWin.println()
        ret = totalWin.reduce { mul, el -> mul * el }
        println(ret)
        return ret
    }

    fun quadraticEquation(t: Long, d: Long): Long {
        val delta = (t * t) - (4 * d)
        val r = (t - sqrt(delta.toDouble())) / 2
        val ceiledR = ceil(r.toDouble())
        return ceiledR.toLong()
    }

    fun part2(input: List<String>): Long {
        var ret: Long = 0
        val time = parsePartTwo(input[0]).toLong()
        val distance = parsePartTwo(input[1]).toLong()

        time.println()
        distance.println()

        val r = quadraticEquation(time, distance)
        println("R is $r")
        ret = time - r*2 + 1
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    part1(testInput)
    part2(testInput)

    val input = readInput("Day06")
//    part1(input).println()
    part2(input).println()
}
