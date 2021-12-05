package Challanges

import Utils.DataReader

object Day5_Hydrothermal_Venture {

    fun part1() {
        val fileLines = DataReader.readFileOfStrings("src/main/resources/day5_hydrothermal_venture.txt")

        val lines = mutableListOf<Array<Array<Int>>>()

        // Convert file into lines
        for(line in fileLines) {
            val stringSegments = mutableListOf<String>()
            line.split("->").forEach { it ->
                it.split(",", " ").forEach {
                if(it.replace(" ", "") != "")
                    stringSegments.add(it.replace(" ", "").replace(" ", ""))
            }}

            val numericLine = Array<Array<Int>>(2) {Array(2) {0} }
            numericLine[0][0] = stringSegments[0].toInt()
            numericLine[0][1] = stringSegments[1].toInt()
            numericLine[1][0] = stringSegments[2].toInt()
            numericLine[1][1] = stringSegments[3].toInt()
            if(numericLine[0][0] == numericLine[1][0] || numericLine[0][1] == numericLine[1][1])
                lines.add(numericLine)
        }

        // Calculate Array of values, representing space of lines
        val spaceWidth = lines.maxOf { it.maxOf { it[0] } }
        val spaceHeight = lines.maxOf { it.maxOf { it[1] } }
        val space = Array(spaceWidth){Array(spaceHeight){0}}

        for(line in lines) {
            var x1 = line[0][0]
            var y1 = line[0][1]

            var x2 = line[1][0]
            var y2 = line[1][1]

            if(x1 == x2) {
                val yMax = y1.coerceAtLeast(y2)
                val yMin = y1.coerceAtMost(y2)

                for(y in yMin..yMax) {
                    space[x1-1][y-1] ++
                }
            }

            else if(y1 == y2) {
                val xMax = x1.coerceAtLeast(x2)
                val xMin = x1.coerceAtMost(x2)

                for(x in xMin..xMax) {
                    space[x-1][y1-1] ++
                }
            }
        }

        val overlappingPoints = space.sumOf{it.sumOf {
            var sumCounter = 0
            if(it > 1)
                sumCounter = 1
            sumCounter
        }}

        println(overlappingPoints)
    }

    // Part 1 function copied, then edited
    fun part2() {
        val fileLines = DataReader.readFileOfStrings("src/main/resources/day5_hydrothermal_venture.txt")

        val lines = mutableListOf<Array<Array<Int>>>()

        // Convert file into lines represented as 2 points
        for(line in fileLines) {
            val stringSegments = mutableListOf<String>()
            line.split("->").forEach { it ->
                it.split(",", " ").forEach {
                    if(it.replace(" ", "") != "")
                        stringSegments.add(it.replace(" ", "").replace(" ", ""))
                }}

            val numericLine = Array<Array<Int>>(2) {Array(2) {0} }
            numericLine[0][0] = stringSegments[0].toInt()
            numericLine[0][1] = stringSegments[1].toInt()
            numericLine[1][0] = stringSegments[2].toInt()
            numericLine[1][1] = stringSegments[3].toInt()
            lines.add(numericLine)
        }

        // Calculate Array of values, representing space of lines
        val spaceWidth = lines.maxOf { it.maxOf { it[0] } }
        val spaceHeight = lines.maxOf { it.maxOf { it[1] } }
        val space = Array(spaceWidth){Array(spaceHeight){0}}

        for(line in lines) {
            var x1 = line[0][0]
            var y1 = line[0][1]

            var x2 = line[1][0]
            var y2 = line[1][1]

            if(x1 == x2) {
                val yMax = y1.coerceAtLeast(y2)
                val yMin = y1.coerceAtMost(y2)

                for(y in yMin..yMax) {
                    space[x1-1][y-1] ++
                }
            }
            else if(y1 == y2) {
                val xMax = x1.coerceAtLeast(x2)
                val xMin = x1.coerceAtMost(x2)

                for(x in xMin..xMax) {
                    space[x-1][y1-1] ++
                }
            }
            else {

                val deltaX = x2 - x1
                val deltaY = y2 - y1
                var vx = 1 //Vorzeichen X
                if(deltaX < 0)
                    vx = -1

                var vy = 1 //Vorzeichen Y
                if(deltaY < 0)
                    vy = -1


                for(i in 0..kotlin.math.abs(deltaX)) {
                    val x = x2 - (i * vx)
                    val y = y2 - (i * vy)
                    space[x-1][y-1] ++
                }
            }
        }

        val overlappingPoints = space.sumOf{it.sumOf {
            var sumCounter = 0
            if(it > 1)
                sumCounter = 1
            sumCounter
        }}

        println(overlappingPoints)
    }


}