package Challanges

import Utils.DataReader

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
    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day14_extended_polymerisation.txt")
        var sequence = inputLines[0]
        val rules = mutableMapOf<String, String>()
        for (line in inputLines.subList(2, inputLines.size - 1)) {
            val parts = line.split(" -> ")
            rules[parts[0]] = parts[1]
        }

        val mapOfChars = mutableMapOf<Char, Int>()
        fun checkRule(first: Char, second: Char, index: Int): String {
            if (index >= 40)
                return ""
            val ruleResult = rules["$first$second"]?.get(0)
            if (ruleResult == null)
                return ""
            var result = checkRule(first, ruleResult, index + 1) + ruleResult + checkRule(ruleResult, second, index + 1)
            mapOfChars[result[0]] = mapOfChars[result[0]]?.plus(1) ?: 1
            return result
        }

        val oldSequence = sequence.toString()
        val sequenceArray = sequence.toCharArray().toMutableList()
        for (i in oldSequence.indices.reversed()) {
            if (i == 0)
                continue
            val first = oldSequence[i - 1]
            val second = oldSequence[i]
            println(i)
            checkRule(first, second, 0)
        }
        sequence = ""
        for (c in sequenceArray)
            sequence += "$c"

        /*for (char in sequence) {
            mapOfChars[char] = mapOfChars[char]?.plus(1) ?: 1
        }*/
        val max = mapOfChars.maxOf { it.value }
        val min = mapOfChars.minOf { it.value }

        println("Result: ${max - min}")
    }

}