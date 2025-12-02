package app

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    part2()
}

fun part2() {
    val ranges = Path("src/Day02.txt").readText().trim().split(',')
    var finalCode = 0L

    for (range in ranges) {
        val endpoints = range.trim().split("-")
        val start = endpoints[0].toLong()
        val end = endpoints[1].toLong()

        for (id in (start..end).map { "$it" }) {
            val len = id.length

            val partsList = mutableSetOf<String>()
            for (parts in 2..len) {
                partsList.clear()

                if (len.rem(parts) != 0) continue
                val dist = len / parts

                for (part in 0..<parts) {
                    partsList.add(id.substring(
                        startIndex = part*dist,
                        endIndex = (part+1)*dist
                    ))
                }

                if(partsList.size == 1) {
                    println("$id -> $partsList")
                    finalCode += id.toLong()
                    break
                }
            }
        }
    }

    println("code: $finalCode")
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