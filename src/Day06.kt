import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    part2_06()
}

fun part2_06() {
    val fullGrid = Path("src/Day06.txt").readText().lines()
    val grid = fullGrid.subList(0, fullGrid.size-1)

    val w = grid[0].length
    val h = grid.size

    val ops = fullGrid.last().trim().split(regex = "\\s+".toRegex())
    var opIdx = 0
    val numbers = mutableListOf<Long>()
    var result = 0L

    for (x in 0..<w) {
        val column = extractColumn(grid, x, h)
        val num = makeNumber(column)
        if (num != 0L) numbers.add(num)
        if(num == 0L || x == w-1){
            val op = ops[opIdx++]
            if (op == "+") result += numbers.sum()
            else {
                var temp = 1L
                for (number in numbers) temp *= number
                result += temp
            }
            numbers.clear()
        }
    }

    println("Result: $result")
}

fun extractColumn(grid: List<String>, x: Int, h: Int): List<Char> {
    val column = List(h) {
        grid[it][x]
    }
    return column
}

fun makeNumber(column: List<Char>): Long {
    val filteredColumn = column.filter { it != ' ' }
    if (filteredColumn.isEmpty()) return 0
    var number = 0L
    for (digit in filteredColumn) {
        val value = (digit-'0').toLong()
        number = number*10 + value
    }
    return number
}

fun part1_06() {
    val grid = processInput()
    val columns = grid[0].size
    var result = 0L

    for ( col in 0..<columns ) {
        val numbers = mutableListOf<Long>()
        var row = 0
        while (true) {
            val term = grid[row][col].trim()

            if (term == "+") {
                result += numbers.sum()
                break
            } else if (term == "*") {
                var temp = 1L
                for (num in numbers) {
                    temp *= num
                }
                result += temp
                break
            } else {
                val num = term.toLong()
                numbers.add(num)
            }
            row += 1
        }

    }

    println(result)
}

fun processInput(): List<List<String>> = readInput("Day06")
    .map {
        it.trim().split(regex = "\\s+".toRegex())
    }