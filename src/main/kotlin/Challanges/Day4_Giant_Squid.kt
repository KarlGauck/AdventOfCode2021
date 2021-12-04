package Challanges

import Utils.DataReader
import java.util.*

object Day4_Giant_Squid {

    fun part1() {
        val lines = DataReader.readFileOfStrings("src/main/resources/day4_giant_squid_bingo.txt")
        val numsToDraw: LinkedList<Int> = LinkedList() // Acts like a queue
        val bingoFields: MutableList<Array<Array<Int>>> = mutableListOf()
        val fieldsMarked: MutableList<Array<Array<Boolean>>> = mutableListOf()

        // Initialize Numbers to draw
        var randomNumsToDrawString = lines[0]
        var splitNumbersString = randomNumsToDrawString.split(',')
        for(number in splitNumbersString) {
            numsToDraw.add(number.toInt())
        }


        // Initialize Boards
        lines.removeAt(0)
        lines.removeAt(0)

        var lineIterator = lines.iterator()
        while(lineIterator.hasNext()) {
            val bingoField: Array<Array<Int>> = Array(5){Array(5){0}}
            for(i in 0..4) {
                val row = Array<Int>(5) {0}
                var numberIndex = 0
                lineIterator.next().split(' ').forEach { s ->
                    if(s.toIntOrNull() != null) {
                        row[numberIndex] = s.toInt()
                        numberIndex++
                    }
                }
                bingoField[i] = row
            }
            bingoFields.add(bingoField)


            if(lineIterator.hasNext())
                lineIterator.next()
        }

        // Initialize Marked
        for(fieldI in bingoFields.indices) {
            fieldsMarked.add(
                Array(5) {
                    Array(5) {
                        false
                    }
                }
            )
        }


        // Next Round -> New Number is set, winner is checked
        var hasWinner = false
        var currentNumberToDraw = 0
        while(!hasWinner && !numsToDraw.isEmpty()) {
            currentNumberToDraw = numsToDraw.pop()
            println(currentNumberToDraw)

            // Mark Fields
            for(fieldWI in bingoFields.withIndex()) {
                for(rowWI in fieldWI.value.withIndex()) {
                    for(numberWI in rowWI.value.withIndex()) {
                        val number = numberWI.value
                        if(number == currentNumberToDraw) {
                            fieldsMarked[fieldWI.index][rowWI.index][numberWI.index] = true
                        }
                    }
                }
            }

            // Calculate Winner
            for(fieldWI in fieldsMarked.withIndex()) {
                val columnTrueCases = Array<Int>(5){0}
                val rowTrueCases = Array<Int>(5){0}
                for(rowWI in fieldWI.value.withIndex()) {
                    rowTrueCases[rowWI.index] = rowWI.value.sumOf {
                        var itAsInt = 0
                        if(it)
                            itAsInt = 1
                        itAsInt
                    }
                    for(markedWI in rowWI.value.withIndex()) {
                        if(markedWI.value)
                            columnTrueCases[markedWI.index] ++
                    }
                }

                if(rowTrueCases.contains(5) || columnTrueCases.contains(5)) {
                    hasWinner = true
                    val sumOfUnmarked = bingoFields[fieldWI.index].withIndex().sumOf { row ->
                        println()
                        row.value.withIndex().sumOf { num ->
                            var countToSum = 0
                            if(!fieldsMarked[fieldWI.index][row.index][num.index]) {
                                countToSum = num.value
                                print("${num.value} ")
                            } else print("- ")

                            countToSum
                        }
                    }
                    println("\n unmarked: $sumOfUnmarked")
                    println("currentNum: $currentNumberToDraw")
                    println("\nresult: ${sumOfUnmarked * currentNumberToDraw}")

                }
            }
        }

    }

    // Almost the same code als part 1, everything copied and then edited
    fun part2() {
        val lines = DataReader.readFileOfStrings("src/main/resources/day4_giant_squid_bingo.txt")
        val numsToDraw: LinkedList<Int> = LinkedList() // Acts like a queue
        val bingoFields: MutableList<Array<Array<Int>>> = mutableListOf()
        val fieldsMarked: MutableList<Array<Array<Boolean>>> = mutableListOf()
        var fieldsWon: Array<Boolean>

        // Initialize Numbers to draw
        var randomNumsToDrawString = lines[0]
        var splitNumbersString = randomNumsToDrawString.split(',')
        for(number in splitNumbersString) {
            numsToDraw.add(number.toInt())
        }


        // Initialize Boards
        lines.removeAt(0)
        lines.removeAt(0)

        var lineIterator = lines.iterator()
        while(lineIterator.hasNext()) {
            val bingoField: Array<Array<Int>> = Array(5){Array(5){0}}
            for(i in 0..4) {
                val row = Array<Int>(5) {0}
                var numberIndex = 0
                lineIterator.next().split(' ').forEach { s ->
                    if(s.toIntOrNull() != null) {
                        row[numberIndex] = s.toInt()
                        numberIndex++
                    }
                }
                bingoField[i] = row
            }
            bingoFields.add(bingoField)


            if(lineIterator.hasNext())
                lineIterator.next()
        }

        // Initialize Marked
        for(fieldI in bingoFields.indices) {
            fieldsMarked.add(
                Array(5) {
                    Array(5) {
                        false
                    }
                }
            )
        }

        // Initialize FieldsWon
        fieldsWon = Array(bingoFields.size){false}


        // Next Round -> New Number is set, winner is checked
        var everyoneWon = false
        var currentNumberToDraw = 0
        while(!everyoneWon && !numsToDraw.isEmpty()) {
            currentNumberToDraw = numsToDraw.pop()
            println(currentNumberToDraw)

            // Mark Fields
            for(fieldWI in bingoFields.withIndex()) {
                for(rowWI in fieldWI.value.withIndex()) {
                    for(numberWI in rowWI.value.withIndex()) {
                        val number = numberWI.value
                        if(number == currentNumberToDraw) {
                            fieldsMarked[fieldWI.index][rowWI.index][numberWI.index] = true
                        }
                    }
                }
            }

            // Calculate Winner
            for(fieldWI in fieldsMarked.withIndex()) {
                if(fieldsWon[fieldWI.index])
                    continue

                val columnTrueCases = Array<Int>(5){0}
                val rowTrueCases = Array<Int>(5){0}
                for(rowWI in fieldWI.value.withIndex()) {
                    rowTrueCases[rowWI.index] = rowWI.value.sumOf {
                        var itAsInt = 0
                        if(it)
                            itAsInt = 1
                        itAsInt
                    }
                    for(markedWI in rowWI.value.withIndex()) {
                        if(markedWI.value)
                            columnTrueCases[markedWI.index] ++
                    }
                }

                if(rowTrueCases.contains(5) || columnTrueCases.contains(5)) {
                    fieldsWon[fieldWI.index] = true

                    if(!fieldsWon.contains(false)) {
                        everyoneWon = true

                        val sumOfUnmarked = bingoFields[fieldWI.index].withIndex().sumOf { row ->
                            println()
                            row.value.withIndex().sumOf { num ->
                                var countToSum = 0
                                if(!fieldsMarked[fieldWI.index][row.index][num.index]) {
                                    countToSum = num.value
                                    print("${num.value} ")
                                } else print("- ")

                                countToSum
                            }
                        }
                        println("\n unmarked: $sumOfUnmarked")
                        println("currentNum: $currentNumberToDraw")
                        println("\nresult: ${sumOfUnmarked * currentNumberToDraw}")
                        return
                    }


                }
            }
        }

    }



}