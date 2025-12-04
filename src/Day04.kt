
fun main() {
    part2_04()
}

fun part2_04() {
    val originalGrid = readInput("Day04")
    val originalWidth = originalGrid[0].length

    // Add one row at front and back and extend width
    val grid = (listOf(".".repeat(originalWidth+2)) +
            originalGrid.map { ".$it." } +
            listOf(".".repeat(originalWidth+2))).toMutableList()

    val w = grid[0].length
    val h = grid.size
    var passcode = 0

    while (true) {
        var toRemove = 0

        for (y in 1..<(h-1)) {
            for (x in 1..<(w-1)) {
                if (grid[y][x] == '@') {
                    val count = countSurroundingRolls(x, y, grid)
                    if (count < 4) {
                        toRemove += 1
                        val newRow = grid[y].mapIndexed { idx, ch -> if(idx == x) '#' else ch }.joinToString(separator = "")
                        grid[y] = newRow
                    }
                }
            }
        }

        for (y in 1..<(h-1)) {
            grid[y] = grid[y].map { if (it == '#') '.' else it }.joinToString(separator = "")
        }
        passcode += toRemove
        if (toRemove == 0) break
    }

    println("Total rolls removed: $passcode")
}

fun part1_04() {
    val originalGrid = readInput("Day04")
    val originalWidth = originalGrid[0].length

    // Add one row at front and back and extend width
    val grid = listOf(".".repeat(originalWidth+2)) +
            originalGrid.map { ".$it." } +
            listOf(".".repeat(originalWidth+2))

    val w = grid[0].length
    val h = grid.size
    var passcode = 0

    for (y in 1..<(h-1)) {
        for (x in 1..<(w-1)) {
            if (grid[y][x] == '@') {
                val count = countSurroundingRolls(x, y, grid)
                if (count < 4) passcode += 1
            }
        }
    }

    println("Rolls to move: $passcode")
}

fun countSurroundingRolls(x: Int, y: Int, grid: List<String>): Int {
    val w = grid[0].length
    val h = grid.size
    var count = 8

    if (x == 0 || x == w-1) return 0
    if (y == 0 || y == h-1) return 0

    count -= if(grid[y-1][x-1] == '.') 1 else 0
    count -= if(grid[y][x-1] == '.') 1 else 0
    count -= if(grid[y+1][x-1] == '.') 1 else 0
    count -= if(grid[y+1][x] == '.') 1 else 0
    count -= if(grid[y+1][x+1] == '.') 1 else 0
    count -= if(grid[y][x+1] == '.') 1 else 0
    count -= if(grid[y-1][x+1] == '.') 1 else 0
    count -= if(grid[y-1][x] == '.') 1 else 0

    return count
}