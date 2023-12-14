fun main() {
    fun isGamePossible(line: String): Boolean {
        val cubeNumbersLimit = mapOf("red" to 12, "green" to 13, "blue" to 14)
        var isPossible = true

        val splitList = line.split(":")
        val cubeSets = splitList[1].split(";")
        for (cubeSet in cubeSets) {
            val cubeNumberColornames = cubeSet.split(",")
            for (cubeNumberColorname in cubeNumberColornames) {
                val splitCubeNumberColorname = cubeNumberColorname.trim().split(" ")
                val cubeColorNumber = splitCubeNumberColorname[0].toInt()
                val cubeColorName = splitCubeNumberColorname[1]

                when (cubeColorName) {
                    "red" -> {
                        if (cubeColorNumber > cubeNumbersLimit["red"]!!) {
                            isPossible = false
                            break
                        }
                    }
                    "green" -> {
                        if (cubeColorNumber > cubeNumbersLimit["green"]!!) {
                            isPossible = false
                            break
                        }
                    }
                    "blue" -> {
                        if (cubeColorNumber > cubeNumbersLimit["blue"]!!) {
                            isPossible = false
                            break
                        }
                    }
                    else -> {
                        println("Other Cube Color")
                        break
                    }
                }
            }
        }

        isPossible.println()
        return isPossible
    }

    fun findFewestCubeNumbers(line: String): MutableMap<String, Int> {
        val fewestCubeNumbers = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)

        val splitList = line.split(":")
        val cubeSets = splitList[1].split(";")
        for (cubeSet in cubeSets) {
            val cubeNumberColornames = cubeSet.split(",")
            for (cubeNumberColorname in cubeNumberColornames) {
                val splitCubeNumberColorname = cubeNumberColorname.trim().split(" ")
                val cubeColorNumber = splitCubeNumberColorname[0].toInt()
                val cubeColorName = splitCubeNumberColorname[1]

                when (cubeColorName) {
                    "red" -> {
                        if (cubeColorNumber > fewestCubeNumbers["red"]!!) {
                            fewestCubeNumbers["red"] = cubeColorNumber
                        }
                    }
                    "green" -> {
                        if (cubeColorNumber > fewestCubeNumbers["green"]!!) {
                            fewestCubeNumbers["green"] = cubeColorNumber
                        }
                    }
                    "blue" -> {
                        if (cubeColorNumber > fewestCubeNumbers["blue"]!!) {
                            fewestCubeNumbers["blue"] = cubeColorNumber
                        }
                    }
                    else -> {
                        println("Other Cube Color")
                        break
                    }
                }
            }
        }

        return fewestCubeNumbers
    }

    fun part1(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            println(line)
            val (gameID, _) = splitLineIDAndContent(line)
//            println(gameID)
            if(isGamePossible(line)) ret += gameID
        }
        println(ret)
        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            println(line)
//            val gameID = getGameID(line)
            val fewestCubeNumbers = findFewestCubeNumbers(line)
            fewestCubeNumbers.println()
            val power = fewestCubeNumbers.values.reduce { acc, i -> acc * i }
            power.println()
            ret += power
        }
        println(ret)
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
//    part1(testInput)
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
//    part1(input).println()
    part2(input).println()
}
