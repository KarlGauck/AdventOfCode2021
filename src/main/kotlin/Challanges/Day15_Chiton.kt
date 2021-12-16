package Challanges

import Utils.DataReader

object Day15_Chiton {

    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day15_chiton.txt")
        val numbers = mutableListOf<MutableList<Int>>()
        for(line in inputLines) {
            val numbersInLine = mutableListOf<Int>()
            for(char in line.toCharArray()) {
                numbersInLine.add(char.digitToInt())
            }
            numbers.add(numbersInLine)
        }

        val riskLevels = Array<Array<Int>>(numbers.size) {Array(numbers[0].size){0} }
        fun initRiskLevels(line: Int, x: Int) {
            if(line == 0 && x == 0)
                riskLevels[line][x] = numbers[line][x]

        }
    }

    fun part2() {

    }

}