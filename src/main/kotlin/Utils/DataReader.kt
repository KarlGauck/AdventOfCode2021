package Utils

import java.io.File

object DataReader {

    fun readFileOfIntegers(fileName: String): MutableList<Int?> {
        return File(fileName).useLines { (it.map { it.toIntOrNull() }).toMutableList() }
    }

    fun readFileOfStrings(fileName: String): MutableList<String> {
        return File(fileName).useLines { it.toMutableList() }
    }

}