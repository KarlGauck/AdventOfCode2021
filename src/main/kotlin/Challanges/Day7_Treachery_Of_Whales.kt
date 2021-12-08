package Challanges

import Utils.DataReader
import java.lang.Math.abs

object Day7_Treachery_Of_Whales {

    fun part1() {
        val crabHeightsString = DataReader.readFileOfStrings("src/main/resources/day7_treachery_of_whales.txt")
        val crabHeights = mutableListOf<Int>()
        crabHeightsString[0].split(",").forEach {
            crabHeights.add(it.toInt())
        }

        var max = crabHeights.maxOf { it }
        var min = crabHeights.minOf { it }


        var finalSum = Int.MAX_VALUE

        for(possibleHeight in min..max) {
            var sum = 0
            for(height in crabHeights) {
                sum += kotlin.math.abs(possibleHeight - height)
            }
            println(sum)
            if(sum < finalSum)
                finalSum = sum
        }



        println(finalSum)
    }

    fun part2() {
        val crabHeightsString = DataReader.readFileOfStrings("src/main/resources/day7_treachery_of_whales.txt")
        val crabHeights = mutableListOf<Int>()
        crabHeightsString[0].split(",").forEach {
            crabHeights.add(it.toInt())
        }

        var max = crabHeights.maxOf { it }
        var min = crabHeights.minOf { it }


        var finalSum = Int.MAX_VALUE

        for(possibleHeight in min..max) {
            var sum = 0
            for(height in crabHeights) {
                for(i in kotlin.math.abs(possibleHeight - height) downTo 1)
                    sum += i
            }
            println(sum)
            if(sum < finalSum)
                finalSum = sum
        }



        println(finalSum)
    }

}