package Challanges

import Utils.DataReader
import java.util.*

object Day10_Syntax_Scoring {

    fun part1() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day10_syntax_scoring.txt")

        var sumOfError = 0

        var numOfCorrupted = 0

        throughLines@
        for(line in inputLines) {
            val chunkOpeningChars = Stack<Char>()
            for(char in line.toCharArray()) {
                if(char in arrayOf('(', '[', '{', '<')) {
                    chunkOpeningChars.push(char)
                }
                else if(char in arrayOf(')', ']', '}', '>')) {
                    val openingChar = chunkOpeningChars.pop()
                    var closingCharMatching = true
                    when(char) {
                        ')' -> if(openingChar != '(') {
                            closingCharMatching = false
                            sumOfError += 3
                        }
                        ']' -> if(openingChar != '[') {
                            closingCharMatching = false
                            sumOfError += 57
                        }
                        '}' -> if(openingChar != '{') {
                            closingCharMatching = false
                            sumOfError += 1197
                        }
                        '>' -> if(openingChar != '<') {
                            closingCharMatching = false
                            sumOfError += 25137
                        }
                    }
                    if(!closingCharMatching) {
                        numOfCorrupted ++
                        continue@throughLines
                    }
                }
            }
        }

        println("Sum of Error: $sumOfError")
        println(numOfCorrupted)

    }

    fun part2() {
        val inputLines = DataReader.readFileOfStrings("src/main/resources/day10_syntax_scoring.txt")

        var incompleteLines = mutableListOf<String>()


        // Part 1: Figure out all not currupted but incomplete lines
        throughLines@
        for(line in inputLines) {
            val chunkOpeningChars = Stack<Char>()
            for(char in line.toCharArray()) {
                if(char in arrayOf('(', '[', '{', '<')) {
                    chunkOpeningChars.push(char)
                }
                else if(char in arrayOf(')', ']', '}', '>')) {
                    val openingChar = chunkOpeningChars.pop()
                    var closingCharMatching = true
                    when(char) {
                        ')' -> if(openingChar != '(') closingCharMatching = false
                        ']' -> if(openingChar != '[') closingCharMatching = false
                        '}' -> if(openingChar != '{') closingCharMatching = false
                        '>' -> if(openingChar != '<') closingCharMatching = false
                    }
                    if(!closingCharMatching) {
                        continue@throughLines
                    }
                }
            }
            if(chunkOpeningChars.size != 0)
                incompleteLines.add(line)
        }


        // Part 2: Figure out the different scores
        val scores = mutableListOf<Long>()
        for(line in incompleteLines) {
            val chunkOpeningChars = Stack<Char>()
            for(char in line.toCharArray()) {
                if(char in arrayOf('(', '[', '{', '<')) {
                    chunkOpeningChars.push(char)
                }
                else if(char in arrayOf(')', ']', '}', '>')) {
                    chunkOpeningChars.pop()
                }
            }
            var sumOfCharacterScores = 0.toLong()
            while(!chunkOpeningChars.isEmpty()) {
                val char = chunkOpeningChars.pop()
                sumOfCharacterScores *= 5
                when(char) {
                    '(' -> sumOfCharacterScores += 1
                    '[' -> sumOfCharacterScores += 2
                    '{' -> sumOfCharacterScores += 3
                    '<' -> sumOfCharacterScores += 4
                    else -> println("else")
                }
            }
            println(sumOfCharacterScores)
            scores.add(sumOfCharacterScores)
        }

        // Part 3: Sort list and get middle score
        val sorted = scores.toSortedSet().toMutableList().sorted()
        println("Result: ${sorted[sorted.size/2]}")

    }

}