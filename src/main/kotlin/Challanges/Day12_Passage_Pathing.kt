package Challanges

import Utils.DataReader
import java.util.*

object Day12_Passage_Pathing {

    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day12_passage_pathing.txt")
        val caveConnections = mutableMapOf<String, MutableList<String>>()
        for(line in inputLines) {
            val chars = line.split("-")
            if(caveConnections[chars[0]]==null)
                caveConnections[chars[0]] = mutableListOf()
            if(caveConnections[chars[1]]==null)
                caveConnections[chars[1]] = mutableListOf()

            caveConnections[chars[0]]!!.add(chars[1])
            caveConnections[chars[1]]!!.add(chars[0])
        }

        var sumOfPaths = 0
        val pathPositions = mutableListOf(PathPosition("start", mutableListOf("start")))
        while(!pathPositions.isEmpty()) {
            for(pos in pathPositions.toTypedArray()) {
                val currentCave = pos.currentCave
                val outTaken = pos.passedThroughCaves.toMutableList()
                outTaken.removeAll {it.lowercase() != it}
                outTaken.removeAll {!(it in caveConnections[currentCave]!!)}
                for(neighbourCave in caveConnections[currentCave]!!) {
                    if(neighbourCave == "end") {
                        sumOfPaths++
                        continue
                    }

                    val isLowerCase = neighbourCave.lowercase(Locale.getDefault()) == neighbourCave

                    if(isLowerCase && neighbourCave in pos.passedThroughCaves)
                        continue
                    val newPassedThroughCaves = pos.passedThroughCaves.toMutableList()
                    newPassedThroughCaves.add(neighbourCave)
                    pathPositions.add(PathPosition(neighbourCave, newPassedThroughCaves))
                }
                pathPositions.remove(pos)
            }
        }


        println("Sum Of Paths: $sumOfPaths")

    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day12_passage_pathing.txt")
        val caveConnections = mutableMapOf<String, MutableList<String>>()
        for(line in inputLines) {
            val chars = line.split("-")
            if(caveConnections[chars[0]]==null)
                caveConnections[chars[0]] = mutableListOf()
            if(caveConnections[chars[1]]==null)
                caveConnections[chars[1]] = mutableListOf()

            caveConnections[chars[0]]!!.add(chars[1])
            caveConnections[chars[1]]!!.add(chars[0])
        }

        var sumOfPaths = 0
        val pathPositions = mutableListOf(PathPositionP2("start", mutableListOf("start"), false))
        while(!pathPositions.isEmpty()) {

            var sumOfPossibilities = 0
            val size = pathPositions.size
            var ind = 0
            for(pos in pathPositions.toTypedArray()) {
                ind++

                if(ind % 500 == 0) {
                    var eq = "["
                    for(i in 1..((ind.toDouble()/size)*50).toInt())
                        eq += "="
                    eq += ">"
                    for(i in ((ind.toDouble()/size)*50).toInt()..50)
                        eq += "."
                    eq += "] "
                    if(ind % 2000 < 500)
                        eq += "[|]"
                    else if(ind % 2000 < 1000)
                        eq += "[/]"
                    else if(ind % 2000 < 1500)
                        eq += "[-]"
                    else
                        eq += "[\\]"
                    print("\rsize: $size  -> $eq    [${pathPositions.sumOf {
                        var r = 0
                        if(it.passedThroughSmallCaveTwice)
                            r = 1
                        r
                    }}]   [${"%.2f".format(sumOfPossibilities.toDouble() / size)}]")
                }


                val currentCave = pos.currentCave
                val outTaken = pos.passedThroughCaves.toMutableList()
                outTaken.removeAll {it.lowercase() != it}
                outTaken.removeAll {!(it in caveConnections[currentCave]!!)}
                for(neighbourCave in caveConnections[currentCave]!!) {
                    if(neighbourCave == "end") {
                        sumOfPaths++
                        continue
                    }
                    if(neighbourCave == "start")
                        continue

                    val isLowerCase = neighbourCave.lowercase(Locale.getDefault()) == neighbourCave
                    var newPassedThroughSmallCavesTwice = pos.passedThroughSmallCaveTwice

                    if(isLowerCase && neighbourCave in pos.passedThroughCaves && pos.passedThroughSmallCaveTwice)
                        continue
                    else if(isLowerCase && neighbourCave in pos.passedThroughCaves && !pos.passedThroughSmallCaveTwice) {
                        newPassedThroughSmallCavesTwice = true
                    }
                    val newPassedThroughCaves = pos.passedThroughCaves.toMutableList()
                    sumOfPossibilities++
                    newPassedThroughCaves.add(neighbourCave)
                    pathPositions.add(PathPositionP2(neighbourCave, newPassedThroughCaves, newPassedThroughSmallCavesTwice))
                }
                pathPositions.remove(pos)
            }
            if(ind > 500)
                println()
        }

        println("Sum Of Paths: $sumOfPaths")

    }

    data class PathPosition(
        val currentCave: String,
        val passedThroughCaves: MutableList<String>
    )

    data class PathPositionP2(
        val currentCave: String,
        val passedThroughCaves: MutableList<String>,
        val passedThroughSmallCaveTwice: Boolean
    )

}