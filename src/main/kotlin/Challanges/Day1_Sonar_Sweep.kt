package Challanges

import Utils.DataReader

object Day1_Sonar_Sweep {

    fun part1() {
        val numbers = DataReader.readFileOfIntegers("src/main/resources/day1_sonar_sweep_fabius.txt")

        var lastNumber = Int.MAX_VALUE
        var countOfIncreases = 0
        for(number in numbers) {
            if(number == null)
                continue
            if(number > lastNumber)
                countOfIncreases ++
            lastNumber = number
        }

        println("Times, value has increased: $countOfIncreases")
    }

    fun part2() {
        val numbers = DataReader.readFileOfIntegers("src/main/resources/day1_sonar_sweep_fabius.txt")

        var lastNumber = Int.MAX_VALUE
        var secondLastNumber = Int.MAX_VALUE

        var lastSum = Int.MAX_VALUE
        var countOfSumIncreases = 0

        var runVar = 0
        for(number in numbers) {
            if(number == null)
                continue

            val sum = number + lastNumber + secondLastNumber
            if(sum > lastSum && runVar > 2)
                countOfSumIncreases ++
            lastSum = sum
            secondLastNumber = lastNumber
            lastNumber = number

            runVar ++
        }

        println("Times, the overall window has increased: $countOfSumIncreases")
    }

}