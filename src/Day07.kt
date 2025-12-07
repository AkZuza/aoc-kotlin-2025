import kotlin.math.max

fun main() {
    part1_07()
    part2_07()
}
fun part2_07() {
    val grid = readInput("Day07").map { row ->
        return@map row.map { ch ->
            return@map when (ch) {
                '^' -> -1L
                'S' -> 1L
                else -> 0L
            }
        }
    }
    val processedInput = mutableListOf<List<Long>>()
    grid.forEachIndexed { idx, row ->
        val newRow = if (idx == 0) row
        else {
            val prow = processedInput.last()
            var newRow = row.toMutableList()
            val len = newRow.size
            for (i in 0..<len) {
                val num = newRow[i]
                if (num == -1L) {
                    if (i > 0) newRow[i-1] += prow[i]
                    if (i < len-1) newRow[i+1] += prow[i]
                } else if (num != 0L) newRow[i] = max(num, 0) + max(prow[i], 0)
                else newRow[i] = max(prow[i], 0)
            }
            newRow
        }
        processedInput.add(newRow)
    }

    val result = processedInput.last().sum()
    println("Part2: $result")
}

fun part1_07() {
    val grid = readInput("Day07")
    val processedInput = mutableListOf<String>()

    grid.forEachIndexed { idx, row ->
        val newRow = when (idx) {
            0 -> row
            1 -> row.mapIndexed { pos, ch -> if (pos == grid[0].indexOf('S')) '|' else ch }.joinToString("")
            else -> row.mapIndexed { pos, ch ->
                val prow = processedInput.last()
                if (pos < row.length-1 && row[pos+1] == '^' && prow[pos+1] == '|') return@mapIndexed '|'
                if (pos > 0 && row[pos-1] == '^' && prow[pos-1] == '|') return@mapIndexed '|'
                if (prow[pos] == '|' && row[pos] != '^') return@mapIndexed '|'
                return@mapIndexed ch
            }.joinToString("")
        }

        processedInput.add(newRow)
    }

    var count = 0L
    for (i in 1..<processedInput.size) {
        val prow = processedInput[i-1]
        val row = processedInput[i]

        row.forEachIndexed { idx, ch ->
            val pch = prow[idx]
            if (ch == '^' && pch == '|') count += 1
        }
    }

    println("Part1: $count")
}