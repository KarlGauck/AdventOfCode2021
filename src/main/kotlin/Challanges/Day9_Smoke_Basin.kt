package Challanges

import Utils.DataReader

object Day9_Smoke_Basin {

    fun part1() {
        val lines = DataReader.readFileOfStrings("src/main/resources/day9_smoke_basin.txt")
        val heightMap = Array<Array<Int>>(lines.size){Array(lines[0].length){0}}

        for (lineW in lines.withIndex()) {
            val line = lineW.value
            for (charW in line.toCharArray().withIndex()) {
                val char = charW.value

                heightMap[lineW.index][charW.index] = char.digitToInt()
            }
        }

        var sumOfLowPoints = 0

        for(y in heightMap.indices) {
            val line = heightMap[y]
            for(x in line.indices) {
                val value = line[x]
                var allNeighboursSmaller = true

                for(dx in -1..1)
                    for(dy in -1..1) {
                        if(!((x + dx) in line.indices) || !((y + dy) in heightMap.indices))
                            continue
                        if(dx == 0 && dy == 0)
                            continue

                        if(heightMap[y+dy][x+dx] <= value)
                            allNeighboursSmaller = false
                    }

                if(allNeighboursSmaller)
                    sumOfLowPoints += value+1
            }
        }

        println("sumOfLowPoints: $sumOfLowPoints")
    }


    fun part2() {
        val lines = DataReader.readFileOfStrings("src/main/resources/day9_smoke_basin.txt")
        val heightMap = Array<Array<Int>>(lines.size){Array(lines[0].length){0}}

        for (lineW in lines.withIndex()) {
            val line = lineW.value
            for (charW in line.toCharArray().withIndex()) {
                val char = charW.value

                heightMap[lineW.index][charW.index] = char.digitToInt()
            }
        }

        var sumOfLowPoints = 0
        val lowPointLocs = mutableListOf<Array<Int>>()

        for(y in heightMap.indices) {
            val line = heightMap[y]
            for(x in line.indices) {
                val value = line[x]
                var allNeighboursSmaller = true

                for(dx in -1..1)
                    for(dy in -1..1) {
                        if(!((x + dx) in line.indices) || !((y + dy) in heightMap.indices))
                            continue
                        if(dx == 0 && dy == 0)
                            continue

                        if(heightMap[y+dy][x+dx] <= value)
                            allNeighboursSmaller = false
                    }

                if(allNeighboursSmaller) {
                    sumOfLowPoints += value + 1
                    lowPointLocs.add(arrayOf(y, x))
                }
            }
        }

        var controlledPoints = Array<Array<Boolean>>(heightMap.size) {Array(heightMap[0].size){false} }
        val areas = mutableListOf<Int>()
        val areaLocations = mutableListOf<Array<Int>>()

        for(point in lowPointLocs) {
            val areaSize = lookupNeighbours(heightMap, controlledPoints, point[0], point[1], 0)
            areas.add(areaSize)
            areaLocations.add(point)
        }

        for(yW in controlledPoints.withIndex()) {
            val y = yW.value
            for(xW in y.withIndex()) {
                val x = xW.value
                var c = "W"
                if(x) c = "."
                if(heightMap[yW.index][xW.index] == 9) c = "#"
                print(" $c")
            }
            println()
        }

        var sorted = areas.sortedDescending()
        println(sorted)
        println("result: ${sorted[0] * sorted[1] * sorted[2]}")
        println("1: ${sorted[0]}")
        println("2: ${sorted[1]}")
        println("3: ${sorted[2]}")

    }

    fun lookupNeighbours(heightMap: Array<Array<Int>>, controlledPoints:Array<Array<Boolean>>, y: Int, x: Int, index: Int): Int {

        if(controlledPoints[y][x])
            return 0
        if(heightMap[y][x] == 9)
            return 0


        var sum = 1
        controlledPoints[y][x] = true
        for(dx in -1..1)
            for(dy in -1..1) {
                if(!((x + dx) in heightMap[0].indices) || !((y + dy) in heightMap.indices))
                    continue
                if(dx == 0 && dy == 0)
                    continue
                if(dx != 0 && dy != 0)
                    continue

                sum += lookupNeighbours(heightMap, controlledPoints, y + dy, x + dx, index+1)
            }
        return sum
    }

}