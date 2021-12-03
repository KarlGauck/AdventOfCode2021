package Challanges

import Utils.DataReader
import Utils.NumericUtils

object Day3_Binary_Diagnostic {

    fun part1() {
        val countOfOnes = mutableMapOf<Int, Int>()
        val binaryNumbers = DataReader.readFileOfStrings("src/main/resources/day3_binary_diagnostic.txt")

        for(binaryNumber in binaryNumbers) {
            for(digit in binaryNumber.indices) {
                if(binaryNumber.substring(digit..digit).toInt() == 1) {
                    if(countOfOnes[digit] == null) {
                        countOfOnes[digit] = 1
                    } else
                        countOfOnes[digit] = countOfOnes[digit]!!+1
                }
            }
        }

        var gammaRate = ""
        var epsilonRate = ""
        val totalDigitCount = 1000 // There are 1000 different numbers in the input
        for(digit in countOfOnes.toSortedMap()) {
            if(digit.value < totalDigitCount/2) {
                gammaRate += "0"
                epsilonRate += "1"
            }
            else {
                gammaRate += "1"
                epsilonRate += "0"
            }
        }

        println(NumericUtils.binaryToDecimal(gammaRate) * NumericUtils.binaryToDecimal(epsilonRate))
    }

    fun part2() {
        val binaryNumbers = DataReader.readFileOfStrings("src/main/resources/day3_binary_diagnostic.txt")

        val valueLength = 12

        val oxygenCriteriaValues = binaryNumbers.toMutableList()
        var digitIndex = 0

        // Run criteria for every digit index as long as it's needed to evaluate the OXYGEN_GENERATOR_RATING
        while(digitIndex < valueLength && oxygenCriteriaValues.size > 1) {

            // Evaluate most Common Digit
            var countOfOnes = 0
            for(value in oxygenCriteriaValues) {
                if (value.substring(digitIndex..digitIndex) == "1")
                    countOfOnes++
            }

            var intendetValue = 0 // Most common digit
            if(countOfOnes < oxygenCriteriaValues.size-countOfOnes)
                intendetValue = 0
            else
                intendetValue = 1

            // Remove wrong values with wrong digit
            val I = oxygenCriteriaValues.iterator()
            while(I.hasNext()) {
                val value = I.next()
                val relevantChar = value.substring(digitIndex..digitIndex)
                if(relevantChar != intendetValue.toString())
                    I.remove()
            }

            digitIndex ++
        }


        val co2CriteriaValues = binaryNumbers.toMutableList()
        digitIndex = 0

        // Run criteria for every digit index as long as it's needed to evaluate the CO2_SCRUBBER_RATING
        while(digitIndex < valueLength && co2CriteriaValues.size > 1) {

            // Evaluate most Common Digit
            var countOfOnes = 0
            for(value in co2CriteriaValues) {
                if (value.substring(digitIndex..digitIndex) == "1")
                    countOfOnes++
            }

            var intendetValue = 0 // Least common digit
            if(co2CriteriaValues.size-countOfOnes <= countOfOnes)
                intendetValue = 0
            else
                intendetValue = 1

            // Remove wrong values with wrong digit
            val I = co2CriteriaValues.iterator()
            while(I.hasNext()) {
                val value = I.next()
                val relevantChar = value.substring(digitIndex..digitIndex)
                if(relevantChar != intendetValue.toString())
                    I.remove()
            }

            digitIndex ++
        }


        println(NumericUtils.binaryToDecimal(oxygenCriteriaValues[0]) * NumericUtils.binaryToDecimal(co2CriteriaValues[0]))
    }

}