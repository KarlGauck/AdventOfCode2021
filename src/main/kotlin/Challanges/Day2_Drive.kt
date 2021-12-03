package Challanges

import Utils.DataReader

object Day2_Drive {

    fun part1() {
        val instructions = DataReader.readFileOfStrings("src/main/resources/day1_drive.txt")

        var depth = 0
        var horizontalPos = 0

        for(instruction in instructions) {
            var change = instruction.substring(instruction.length-1, instruction.length).toInt()
            if(instruction.contains("forward")) {
                horizontalPos += change
            } else if (instruction.contains("up")) {
                depth -= change
            } else if (instruction.contains("down")) {
                depth += change
            }
        }

        println("Horizontal Pos: $horizontalPos   Depth: $depth   Final Result: ${horizontalPos * depth}")
    }

    fun part2() {
        val instructions = DataReader.readFileOfStrings("src/main/resources/day1_drive.txt")

        var depth = 0
        var horizontalPos = 0
        var aim = 0

        for(instruction in instructions) {
            var change = instruction.substring(instruction.length-1, instruction.length).toInt()
            if(instruction.contains("forward")) {
                horizontalPos += change
                depth += aim * change
            } else if (instruction.contains("up")) {
                aim -= change
            } else if (instruction.contains("down")) {
                aim += change
            }
        }

        println("Horizontal Pos: $horizontalPos   Depth: $depth   Final Result: ${horizontalPos * depth}")
    }

}