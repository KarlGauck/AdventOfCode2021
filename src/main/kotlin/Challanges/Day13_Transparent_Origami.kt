package Challanges

import Utils.DataReader

object Day13_Transparent_Origami {

    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day13_transparent_origami.txt")
        val dotLines = inputLines.subList(0, 715)
        val foldLines = inputLines.subList(716, 728)
        val dots = mutableListOf<Array<Int>>()
        for (dot in dotLines) {
            val part = dot.split(",")
            val x = part[0].toInt()
            val y = part[1].toInt()
            dots.add(arrayOf(x, y))
        }

        val maxX = dots.maxOf { it[0] }
        val maxY = dots.maxOf { it[1] }
        for (line in foldLines) {
            val instruction = line.removePrefix("fold along ").split("=")
            val I = dots.iterator()
            while (I.hasNext()) {
                val dot = I.next()
                if (instruction[0] == "x") {
                    val fold = instruction[1].toInt()
                    if (dot[0] in fold..maxX) {
                        val distance = dot[0] - fold
                        val newX = fold - distance
                        if (dots.filter { it[0] == newX && it[1] == dot[1] }.isEmpty())
                            dot[0] = newX
                        else
                            I.remove()
                    }
                } else if (instruction[0] == "y") {
                    val fold = instruction[1].toInt()
                    if (dot[1] in fold..maxY) {
                        val distance = dot[1] - fold
                        val newY = fold - distance
                        if (dots.filter { it[0] == dot[0] && it[1] == newY }.isEmpty())
                            dot[1] = newY
                        else
                            I.remove()
                    }
                }
            }

            var sum = 0
            println(dots.size)

            break
        }

    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day13_transparent_origami.txt")
        val dotLines = inputLines.subList(0, 715)
        val foldLines = inputLines.subList(716, 728)
        val dots = mutableListOf<Array<Int>>()
        for (dot in dotLines) {
            val part = dot.split(",")
            val x = part[0].toInt()
            val y = part[1].toInt()
            dots.add(arrayOf(x, y))
        }

        val maxX = dots.maxOf { it[0] }
        val maxY = dots.maxOf { it[1] }
        for (line in foldLines) {
            val instruction = line.removePrefix("fold along ").split("=")
            val I = dots.iterator()
            while (I.hasNext()) {
                val dot = I.next()
                if (instruction[0] == "x") {
                    val fold = instruction[1].toInt()
                    if (dot[0] in fold..maxX) {
                        val distance = dot[0] - fold
                        val newX = fold - distance
                        if (dots.filter { it[0] == newX && it[1] == dot[1] }.isEmpty())
                            dot[0] = newX
                        else
                            I.remove()
                    }
                } else if (instruction[0] == "y") {
                    val fold = instruction[1].toInt()
                    if (dot[1] in fold..maxY) {
                        val distance = dot[1] - fold
                        val newY = fold - distance
                        if (dots.filter { it[0] == dot[0] && it[1] == newY }.isEmpty())
                            dot[1] = newY
                        else
                            I.remove()
                    }
                }
            }
        }

        val newMaxX = dots.maxOf { it[0] }
        val newMaxY = dots.maxOf { it[1] }
        val newMinX = dots.minOf { it[0] }
        val newMinY = dots.minOf { it[1] }

        for (y in newMinY..newMaxY) {
            for (x in newMinX..newMaxX) {

                if (dots.filter { it[0] == x && it[1] == y }.isEmpty())
                    print("  ")
                else
                    print("X ")
            }
            println()
        }
    }

}