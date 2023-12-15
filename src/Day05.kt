/**
 * https://adventofcode.com/2023/day/5
 * */

data class Day05MapItem(val desStart: Long, val srcStart: Long, val len: Long)
data class Day05MapList(val source: String, val destination: String, val mapList: List<Day05MapItem>)

fun main() {
    fun parseSeeds(line: String): MutableList<Long> {
        val seeds = line.split(": ")[1].trim().split(" ").filterNot { s -> s == "" }.map { s -> s.toLong() }
        return seeds.toMutableList()
    }

    fun mapSrcToDes(src: MutableList<Long>, mapList: Day05MapList): MutableList<Long> {
        val newSrc = mutableListOf<Long>()
        for (s in src) {
            var isMapped = false
            for (mapItem in mapList.mapList) {
                if (s in mapItem.srcStart..(mapItem.srcStart+mapItem.len)) {
                    isMapped = true
                    val diff = s-mapItem.srcStart
                    newSrc.add(mapItem.desStart+diff)
                    break
                }
            }
            if (!isMapped) newSrc.add(s)
        }
        return newSrc
    }

    fun part1(input: List<String>): Long {
        var ret: Long = 0

        var src = mutableListOf<Long>()
        var isMapBlock = false
        var currentSrc = ""
        var currentDes = ""
        val currenMapList = mutableListOf<Day05MapItem>()

        for ((lineNumber, line) in input.withIndex()) {
            line.println()
            val lineType = line.split(":")[0]

            when (lineType) {
                "seeds" -> {
                    src = parseSeeds(line)
                }
                "seed-to-soil map" -> {
                    isMapBlock = true
                    currentSrc = "seed"
                    currentDes = "soil"
                    continue
                }
                "soil-to-fertilizer map" -> {
                    isMapBlock = true
                    currentSrc = "soil"
                    currentDes = "fertilizer"
                    continue
                }
                "fertilizer-to-water map" -> {
                    isMapBlock = true
                    currentSrc = "fertilizer"
                    currentDes = "water"
                    continue
                }
                "water-to-light map" -> {
                    isMapBlock = true
                    currentSrc = "water"
                    currentDes = "light"
                    continue
                }
                "light-to-temperature map" -> {
                    isMapBlock = true
                    currentSrc = "light"
                    currentDes = "temperature"
                    continue
                }
                "temperature-to-humidity map" -> {
                    isMapBlock = true
                    currentSrc = "temperature"
                    currentDes = "humidity"
                    continue
                }
                "humidity-to-location map" -> {
                    isMapBlock = true
                    currentSrc = "humidity"
                    currentDes = "location"
                    continue
                }
                "" -> {
                    isMapBlock = false

                    val srcDesMap = Day05MapList(currentSrc, currentDes, currenMapList)
//                    println("Current Map is $srcDesMap")
                    if (lineNumber > 1) src = mapSrcToDes(src, srcDesMap)
                    println("$currentDes is: $src")

                    currentSrc = ""
                    currentDes = ""
                    currenMapList.clear()
                    continue
                }
            }

            if (isMapBlock) {
                val splitNumbers = line.split(" ").filterNot { s -> s == "" }.map { s -> s.toLong() }
                val mapItem = Day05MapItem(splitNumbers[0], splitNumbers[1], splitNumbers[2])
                currenMapList.add(mapItem)
//                currenMapList.println()
//                continue
            }

            if (lineNumber == input.lastIndex) {
                val srcDesMap = Day05MapList(currentSrc, currentDes, currenMapList)
                src = mapSrcToDes(src, srcDesMap)
                println("$currentDes is: $src")
                ret = src.min()
            }
        }

        ret.println()
        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        for ((lineNumber, line) in input.withIndex()) {
            line.println()
        }

        ret.println()
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
//    part1(testInput)
    check(part1(testInput) == 35.toLong())
//    part2(testInput)
//    check(part2(testInput) == 1)

    val input = readInput("Day05")
    part1(input).println()
//    part2(input).println()
}