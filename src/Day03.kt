import kotlin.math.max

fun main() {
    part1()
}

fun part1() {
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

