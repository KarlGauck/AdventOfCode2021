package Utils

import kotlin.math.abs
import kotlin.math.pow

object NumericUtils {

    fun getIntegerDigit(int: Int, index: Int): Int {

        // Return if required digit doesn't exist
        if(abs(int) < 0)
            return -1
        if(abs(int) < 10.0.pow(index.toDouble()))
            return -1

        println(abs(int))
        println(10*index)

        if(index < 0)
            return -1

        var runIndex = index
        var runInteger = abs(int)
        while(runIndex > 0) {
            runInteger /= 10
            runIndex--
        }

        // The integer, that has to be substracted to get only the last digit of an integer
        val overloadedIntegerPart = (runInteger / 10) * 10
        return runInteger - overloadedIntegerPart
    }

    fun getIntegerLength(int: Int): Int {
        var runInt = abs(int)
        var length = 1
        while(runInt > 10) {
            runInt /= 10
            length++
        }
        return length
    }

    fun binaryToDecimal(binary: String): Int {
        var int = 0
        for(char in binary.toCharArray().reversed().withIndex()) {
            if(char.value == '1')
                int += 2.0.pow(char.index.toDouble()).toInt()
        }
        return int
    }

}