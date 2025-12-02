package app

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {

}

fun part2() {

}

fun part1() {
    val ranges = Path("src/Day02.txt").readText().trim().split(',')
    var finalCode = 0L

    for (range in ranges) {
        val endpoints = range.trim().split("-")
        val start = endpoints[0].toLong()
        val end = endpoints[1].toLong()

        for (id in (start..end).map { "$it" }) {
            if (id.length.rem(2) == 0) {
                val first = id.substring(startIndex = 0, endIndex = id.length/2)
                val second = id.substring(id.length/2)

                if (first == second) {
                    finalCode += id.toLong()
                }
            }
        }
    }
    println("code: $finalCode")
}