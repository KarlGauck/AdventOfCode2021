package Challanges

import Utils.DataReader

object Day6_Lanternfish {

    fun part1() {
        val lanternfishString = DataReader.readFileOfStrings("src/main/resources/day6_lanternfish.txt")
        val lanternFishs = mutableListOf<Int>()
        lanternfishString[0].split(",").forEach {lanternFishs.add(it.toInt())}

        for(day in 0..79) {
            val newFishs = mutableListOf<Int>()

            for(fishIndex in lanternFishs.indices) {
                lanternFishs[fishIndex] --
                if(lanternFishs[fishIndex] < 0) {
                    lanternFishs[fishIndex] = 6
                    newFishs.add(8)
                }
            }

            lanternFishs.addAll(newFishs)
        }

        println(lanternFishs.size)

    }

    fun part2() {
        val lanternfishString = DataReader.readFileOfStrings("src/main/resources/day6_lanternfish.txt")
        val lanternFishs = mutableListOf<Int>()
        lanternfishString[0].split(",").forEach {lanternFishs.add(it.toInt())}

        val lanternFishsMap = mutableMapOf<Int, Long>()
        for(dayToBreed in 0..8) {
            lanternFishsMap[dayToBreed] = lanternFishs.sumOf {
                var result = 0
                if(it == dayToBreed)
                    result = 1
                result
            }.toLong()
        }

        for(day in 0..255) {
            val numOfZeros = lanternFishsMap[0]
            for(dayToBreed in 1..8) {
                lanternFishsMap[dayToBreed - 1] = lanternFishsMap[dayToBreed]!!
            }
            lanternFishsMap[6] = lanternFishsMap[6]?.plus(numOfZeros!!) ?: 0
            lanternFishsMap[8] = numOfZeros!!
        }

        var sum: Long = 0
        for(fishs in lanternFishsMap) {
            sum += fishs.value
            println(fishs.value)
        }
        println(sum)

    }

}