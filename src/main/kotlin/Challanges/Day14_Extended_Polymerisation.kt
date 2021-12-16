package Challanges

import Utils.DataReader
import kotlin.math.pow

object Day14_Extended_Polymerisation {


    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day14_extended_polymerisation.txt")
        var sequence = inputLines[0]
        val rules = mutableMapOf<String, String>()
        for (line in inputLines.subList(2, inputLines.size - 1)) {
            val parts = line.split(" -> ")
            rules[parts[0]] = parts[1]
        }

        for (step in 1..10) {
            val oldSequence = sequence.toString()
            val sequenceArray = sequence.toCharArray().toMutableList()
            for (i in oldSequence.indices.reversed()) {
                if (i == 0)
                    continue
                val first = oldSequence[i - 1]
                val second = oldSequence[i]
                val result = rules["$first$second"]
                if (result != null) {
                    sequenceArray.add(i, result[0])
                }
            }
            sequence = ""
            for (c in sequenceArray)
                sequence += "$c"
        }

        val mapOfChars = mutableMapOf<Char, Int>()
        for (char in sequence) {
            mapOfChars[char] = mapOfChars[char]?.plus(1) ?: 1
        }
        val max = mapOfChars.maxOf { it.value }
        val min = mapOfChars.minOf { it.value }

        println("Result: ${max - min}")
        println("max: $max  min: $min")
    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day14_extended_polymerisation.txt")
        var sequence = inputLines[0]
        val rules = mutableMapOf<String, String>()
        for (line in inputLines.subList(2, inputLines.size - 1)) {
            val parts = line.split(" -> ")
            rules[parts[0]] = parts[1]
        }

        val mapOfChars = mutableMapOf<Char, Long>()
        val iterations = 10
        val resultMemory = mutableMapOf<Int, MutableMap<String, MutableMap<Char, Long>>>()
        fun checkRule(first: Char, second: Char, index: Int): MutableMap<Char, Long> {
            if (index >= iterations)
                return mutableMapOf()

            var entryResultMap: MutableMap<Char, Long>? = null
            if(resultMemory[index] != null && resultMemory[index]?.get("$first$second") != null)
                entryResultMap = resultMemory[index]?.get("$first$second") ?: mutableMapOf()

            if(entryResultMap != null) {
//                println("aha: ${mapOfChars.size}")
                for(entry in entryResultMap) {
//                    println("entry")
                    mapOfChars[entry.key] = mapOfChars[entry.key]?.plus(entry.value) ?: 0
                }
//                println("kk: ${mapOfChars.size}")
                return entryResultMap
            }

            val ruleResult = rules["$first$second"]?.get(0) ?: return mutableMapOf()

            entryResultMap = checkRule(first, ruleResult, index+1)
            for(entry in checkRule(ruleResult, second, index+1)) {
                entryResultMap[entry.key] = entryResultMap[entry.key]?.plus(entry.value) ?: entry.value
            }
            entryResultMap[ruleResult] = entryResultMap[ruleResult]?.plus(1) ?: 1

            if(resultMemory[index] == null)
                resultMemory[index] = mutableMapOf()
            resultMemory[index]!!["$first$second"] = entryResultMap

            mapOfChars[ruleResult] = mapOfChars[ruleResult]?.plus(1) ?: 1
            return entryResultMap
        }

        val oldSequence = sequence.toString()
        val sequenceArray = sequence.toCharArray().toMutableList()
        for (i in oldSequence.indices.reversed()) {
            mapOfChars[oldSequence[i]] = mapOfChars[oldSequence[i]]?.plus(1) ?: 1
            if (i == 0)
                continue
            val first = oldSequence[i - 1]
            val second = oldSequence[i]
            checkRule(first, second, 0)
        }
        sequence = ""
        for (c in sequenceArray)
            sequence += "$c"

        val max = mapOfChars.maxOf { it.value }
        val min = mapOfChars.minOf { it.value }
        val minK = mapOfChars.keys.toMutableList()[mapOfChars.values.indexOf(min)]

        println("Result: ${max - min}")
        println("max: $max   min: $min")
    }

}