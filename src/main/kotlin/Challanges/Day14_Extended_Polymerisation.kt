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

        var comboMap = mutableMapOf<String, Long>()
        val mapOfChars = mutableMapOf<Char, Long>()

        for(charI in sequence.indices) {    // Iteriere durch alle Characters des input-strings
            mapOfChars[sequence[charI]] = mapOfChars[sequence[charI]]?.plus(1) ?: 1     // Addiere den momentanen Character zur Liste der Anzahlen der verschiedenen Character
            if(charI == 0)
                continue
            val first = sequence[charI-1]
            val second = sequence[charI]
            val result = rules["$first$second"]?.get(0)
            result.let {             // Füge die beiden
                comboMap["$first$second"] = comboMap["$first$second"]?.plus(1) ?: 1
            }
        }

        for (step in 1..40) {
            val newComboMap = comboMap.toMutableMap()
            for (comboE in comboMap.entries) {
                val combo = comboE.key              // Char-Kombination, an der im Moment gerechnet wird
                val duplicates = comboE.value       // Anzahl an bigrammen, für die die Operation ausgeführt werden muss
                val first = combo[0]
                val second = combo[1]
                val result = rules["$first$second"] // Der neue Character, der zwischen den Beiden ursprünglichen Charactern eingefügt werden soll
                result.let {    //if(result!=null) result -> it
                    val newCombo1 = "$first$it"
                    val newCombo2 = "$it$second"
                    newComboMap[combo] = newComboMap[combo]?.minus(duplicates) ?: 0                     // Entferne die momentane Char-Komtination aus der Liste
                    newComboMap[newCombo1] = newComboMap[newCombo1]?.plus(duplicates) ?: duplicates     // Füge die erste neu entstandene Char-Kombination hinzu
                    newComboMap[newCombo2] = newComboMap[newCombo2]?.plus(duplicates) ?: duplicates     // Füge die zweite neu entstandene Char-Kombination hinzu
                    mapOfChars[it!![0]] = mapOfChars[it[0]]?.plus(duplicates) ?: duplicates             // Erhöhe die Anzahl des neu entstandenen Characters
                }
            }
            comboMap = newComboMap
        }

        val max = mapOfChars.maxOf { it.value }
        val min = mapOfChars.minOf { it.value }

        println("Result: ${max - min}")
        println("max: $max  min: $min")
    }

}