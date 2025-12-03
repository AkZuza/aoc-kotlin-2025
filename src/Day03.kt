import kotlin.math.max

fun main() {
    part1()
    part2()
}

fun part1() = genericSolution(2)
fun part2() = genericSolution(12)

fun part1BF() {
    val banks = readInput("Day03")
    var passcode = 0L

    // BRUTEFORCE APPROACH
    for (bank in banks) {
        val len = bank.length
        var greatest = 0L
        for (i in 0..<(len-1)) {
            for (j in (i+1)..<len) {
                val first = bank[i]
                val second = bank[j]
                val num = "$first$second".toLong()
                greatest = max(greatest, num)
            }
        }
        passcode += greatest
    }

    println(passcode)
}

fun genericSolution(count: Int = 1) {
    val banks = readInput("Day03")
    var passcode = 0L

    // SORTED MAPS
    for (bank in banks) {
        val sortedMap = emptyMap<Int, Char?>().toSortedMap()
        val len = bank.length
        var joltage = 0L
        repeat(count) {
            var greaterIdx = 0
            for (i in 0..<len) {
                val shouldNull = (sortedMap[i] == null)
                sortedMap[i] = bank[i]
                val num = sortedMap.values.joinToString(separator = "", transform = { digit ->
                    if (digit == null) ""
                    else "$digit"
                }).toLong()
                if (num > joltage) {
                    greaterIdx = i
                    joltage = num
                }

                if (shouldNull)
                    sortedMap[i] = null
            }
            sortedMap[greaterIdx] = bank[greaterIdx]
        }
        passcode += joltage
    }

    println(passcode)
}

