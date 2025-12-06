import kotlin.math.max
import kotlin.math.min

fun main() {
    part2_05()
}

fun part1_05() {
    var code = 0L


    val input = readInput("Day05")
    val (ranges, ids_extra) = input.partition { it.contains("-") }
    val ids = ids_extra.drop(1)
    val freshRanges = List(size = ranges.size) { idx ->
        val (start, end) = ranges[idx].split("-")
        Pair(start.toLong(), end.toLong())
    }

    for (sid in ids) {
        val id = sid.toLong()
        for ( (start, end) in freshRanges) {
            if (id in start..end) {
                code += 1
                break
            }
        }
    }

    println("Fresh Ingredients: $code")
}

fun part2_05() {
    val input = readInput("Day05")
    var code = 0L
    val (ranges, _) = input.partition { it.contains("-") }
    var least = ranges[0].split("-")[0].toLong()
    val freshRanges = List(size = ranges.size) { idx ->
        val (start, end) = ranges[idx].split("-")
        least = min(start.toLong(), min(end.toLong(), least))
        Pair(start.toLong(), end.toLong())
    }.sortedBy { (start, end) -> start }

    val processedRanges = mutableListOf<Pair<Long, Long>>()

    for ( (start, end) in freshRanges) {
        if (processedRanges.isEmpty() || processedRanges.last().second < start) {
            processedRanges.add(Pair(start, end))
        } else {
            processedRanges[processedRanges.size-1] = Pair(processedRanges.last().first, max(end, processedRanges.last().second))
        }
    }

    for ( (start, end) in processedRanges ) {
        code += end - start + 1
    }

    println("Total fresh: $code")
}