import kotlin.math.max
import kotlin.math.min

/**
 * https://adventofcode.com/2023/day/5
 * */

data class Day05MapItem(val desStart: Long, val srcStart: Long, val len: Long)
data class Day05MapList(val source: String, val destination: String, val mapList: List<Day05MapItem>)
data class Day05Range(val rangeStart: Long, val rangeEnd: Long)

fun main() {
    fun parseSeeds(line: String): MutableList<Long> {
        val seeds = line.split(": ")[1].trim().split(" ").filterNot { s -> s == "" }.map { s -> s.toLong() }
        return seeds.toMutableList()
    }

    fun parseRangeSeeds(line: String): MutableList<Day05Range> {
        val seeds = line.split(": ")[1].trim().split(" ").filterNot { s -> s == "" }.map { s -> s.toLong() }
        val rangeList = mutableListOf<Day05Range>()
        var startIndex = 0
        while (startIndex < seeds.lastIndex) {
            val newRange= Day05Range(seeds[startIndex], seeds[startIndex] + seeds[startIndex+1] - 1)
            rangeList.add(newRange)
//            retList.add(seeds[startIndex])
//            retList.add(seeds[startIndex] + seeds[startIndex+1])
            startIndex += 2
        }
//        rangeList.println()
//        retList.println()
//        retList.sorted().println()
        return rangeList
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

    fun part2(input: List<String>): Long {
        var ret: Long = 0
        input.println()

        var seeds = parseRangeSeeds(input[0])
        println("First seeds is $seeds")

        // Get Blocks
        val inputDropped = input.drop(2)
        val blocks = mutableListOf<MutableList<Day05MapItem>>()
        val lastBlock = mutableListOf<Day05MapItem>()

        for ((lineNumber, line) in inputDropped.withIndex()) {
            if (line == "") {
                // https://stackoverflow.com/questions/46846025/how-to-clone-or-copy-a-list-in-kotlin
                blocks.add(lastBlock.toMutableList())
                continue
            }
            if (line.last() == ':') {
                lastBlock.clear()
                continue
            }
            val splitLine = line.split(" ").map { x -> x.toLong() }
            val newMapItem = Day05MapItem(splitLine[0], splitLine[1], splitLine[2])
            lastBlock.add(newMapItem)

            if (lineNumber == inputDropped.lastIndex) {
                blocks.add(lastBlock)
            }
        }
        blocks.println()

        // Processing maps
        var srcList = seeds.toMutableList()
        for (block in blocks) {
            val newList = mutableListOf<Day05Range>()
            println("***************************************")
            while (srcList.size > 0) {
                val src = srcList.removeFirst()
                var isMapped = false
                println("Processing src is $src")

                for (mapItem in block) {
                    println("Processing mapItem is $mapItem")
                    val overlayStart = max(src.rangeStart, mapItem.srcStart)
                    val overlayEnd = min(src.rangeEnd, mapItem.srcStart + mapItem.len - 1)

                    if (overlayStart < overlayEnd) {
                        isMapped = true
                        println("$src is mapped by $mapItem")

                        val newSrcRangeStart = mapItem.desStart - mapItem.srcStart + overlayStart
                        val newSrcRangeEnd = mapItem.desStart - mapItem.srcStart + overlayEnd
                        val newSrcRangge = Day05Range(newSrcRangeStart, newSrcRangeEnd)
                        newList.add(newSrcRangge)

                        if (overlayStart > src.rangeStart) {
                            srcList.add(Day05Range(src.rangeStart, overlayStart - 1))
                        }
                        if (overlayEnd < src.rangeEnd) {
                            srcList.add(Day05Range(overlayEnd + 1, src.rangeEnd))
                        }

                        break
                    }
                }

                if (!isMapped) {
                    println("$src not mapped in the block $block")
                    newList.add(src)
                }
            }
            srcList = newList
            println("Next Src List is: $srcList")
        }

        val lowest = srcList.minBy { it.rangeStart }
        ret = lowest.rangeStart
        println("Result is: $ret")
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
//    part1(testInput)
//    check(part1(testInput) == 35.toLong())
//    part2(testInput)
    check(part2(testInput) == 46.toLong())

    val input = readInput("Day05")
//    part1(input).println()
    part2(input).println()
}