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

    fun part2(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            println(line)
        }
        println(ret)
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    part1(testInput)
    check(part1(testInput) == 288)
//    part2(testInput)
//    check(part2(testInput) == 281)

    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}
