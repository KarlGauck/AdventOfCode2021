package Challanges

import Utils.DataReader

object Day8_Seven_Segment_Search {

    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day8_seven_segment_search.txt")
        val outputLines = mutableListOf<String>()
        for(line in inputLines)
            outputLines.add(line.split("|")[1])

        val outputs = mutableListOf<Array<String>>()
        for(line in outputLines)
            outputs.add((line.split(" ").toTypedArray()))


        var count = 0
        for(outputSet in outputs) {
            for(value in outputSet) {
                if(value.length == 2)
                    count++
                if(value.length == 3)
                    count ++
                if(value.length == 4)
                    count ++
                if(value.length == 7)
                    count ++
            }
        }

        println("Number digits: $count")
    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day8_seven_segment_search.txt")
        val valueSets = mutableListOf<MutableList<String>>()
        for(line in inputLines) {
            val list = mutableListOf<String>()
            list.addAll((line.split("|")[0].split(" ").toMutableList()))
            valueSets.add(list)
        }

        var sumOfAllDecodes = 0

        for(valueSetW in valueSets.withIndex()) {
            val valueSet = valueSetW.value

            var upperSegment = ' '

            val digitStrings = mutableMapOf<Int, String>()
            for(string in valueSet) {
                if(string.length == 2)
                    digitStrings[1] = string
                if(string.length == 3)
                    digitStrings[7] = string
                if(string.length == 4)
                    digitStrings[4] = string
                if(string.length == 7)
                    digitStrings[8] = string
            }
            // 1, 7, 4, 8 sind herausgefunden

            val charOfFour = digitStrings[4]!!.toCharArray()
            for(char in digitStrings[7]!!) {
                if(!charOfFour.contains(char)) {
                    upperSegment = char
                    break
                }
            }

            for(string in valueSet) {
                if(string.length != 6)
                    continue

                var allDigitsFromFourExisting = true
                for(char in digitStrings[4]!!.toCharArray()) {
                    if(!string!!.contains(char))
                        allDigitsFromFourExisting = false
                }
                if(allDigitsFromFourExisting)
                    digitStrings[9] = string
                else {
                    var allDigitsFromOneExisting = true
                    for(char in digitStrings[1]!!.toCharArray()) {
                        if(!string.contains(char))
                            allDigitsFromOneExisting = false
                    }
                    if(allDigitsFromOneExisting)
                        digitStrings[0] = string
                    else
                        digitStrings[6] = string
                }
            }
            // 0, 1, 4, 6, 7, 8, 9 sind herausgefunden

            for(string in valueSet) {
                if(string.length != 5)
                    continue

                var allDigitsFromOneExisting = true
                for(char in digitStrings[1]!!.toCharArray()) {
                    if(!string.contains(char))
                        allDigitsFromOneExisting = false
                }
                if(allDigitsFromOneExisting)
                    digitStrings[3] = string
                else {
                    var fitsIntoSix = true
                    for(char in string.toCharArray()) {
                        if(!digitStrings[6]!!.contains(char))
                            fitsIntoSix = false
                    }
                    if(fitsIntoSix)
                        digitStrings[5] = string
                    else
                        digitStrings[2] = string
                }
            }
            // alle nummern herausgefunden

            println("numbers: ${digitStrings.toSortedMap()}")

            val outputStrings = inputLines[valueSetW.index].split("|")[1].split(" ") as MutableList
            val I = outputStrings.iterator()
            while(I.hasNext()) {
                val next = I.next()
                if(next == "")
                    I.remove()
            }

            var decodecOutput = ""
            for(string in outputStrings) {
                for(entry in digitStrings.toSortedMap().entries) {
                    val entryChars = entry.value.toCharArray().sorted()
                    val stringChars = string.toCharArray().sorted()
                    if(entryChars == stringChars)
                        decodecOutput += entry.key.toString()
                }
            }
            println("decodedOutput: ${outputStrings}")
            sumOfAllDecodes += decodecOutput.toInt()

        }

        println(sumOfAllDecodes)

    }

}