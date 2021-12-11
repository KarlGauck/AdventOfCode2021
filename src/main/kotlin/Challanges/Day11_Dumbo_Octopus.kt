package Challanges

import Utils.DataReader

object Day11_Dumbo_Octopus {

    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day11_dumbo_octopus.txt")
        val grid = Array<Array<Int>>(10){x->Array(10){y->inputLines[y].toCharArray()[x].digitToInt()}}


        var flashCount = 0
        fun flash(x: Int, y: Int) {
            flashCount ++
            for(dx in -1..1)
                for(dy in -1..1) {
                    if(!(x+dx in 0..9) || !(y+dy in 0..9))
                        continue

                    grid[x+dx][y+dy] ++
                    if(grid[x+dx][y+dy] == 10)
                        flash(x+dx, y+dy)
                }
        }

        for(step in 0..99) {

            for(x in grid.indices) {
                for(y in grid[x].indices) {

                    grid[x][y] ++
                    if(grid[x][y] == 10)
                        flash(x, y)

                }
            }
            for(x in grid.indices)
                for(y in grid[x].indices) {
                    if(grid[x][y] > 9)
                        grid[x][y] = 0
                }
        }

        println("flashCount: $flashCount")

    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day11_dumbo_octopus.txt")
        val grid = Array<Array<Int>>(10){x->Array(10){y->inputLines[y].toCharArray()[x].digitToInt()}}


        var flashCount = 0
        fun flash(x: Int, y: Int) {
            flashCount ++
            for(dx in -1..1)
                for(dy in -1..1) {
                    if(!(x+dx in 0..9) || !(y+dy in 0..9))
                        continue

                    grid[x+dx][y+dy] ++
                    if(grid[x+dx][y+dy] == 10)
                        flash(x+dx, y+dy)
                }
        }

        var lastFlashCount = 0
        for(step in 1..10000) {

            for(x in grid.indices) {
                for(y in grid[x].indices) {

                    grid[x][y] ++
                    if(grid[x][y] == 10)
                        flash(x, y)

                }
            }
            for(x in grid.indices)
                for(y in grid[x].indices) {
                    if(grid[x][y] > 9)
                        grid[x][y] = 0
                }

            val stepFlashes = flashCount-lastFlashCount
            lastFlashCount = flashCount

            println(stepFlashes)

            if(stepFlashes==100) {
                println("FirstSyncronizing: $step")
                return
            }
        }

        println("flashCount: $flashCount")

    }

}