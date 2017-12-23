import kotlinPuzzleLibrary.*

fun main(args: Array<String>) {
    val input =
"""
....##.#.#.#...#.##.##.#.
##.####..###..#.#.#.###.#
.#.#...#.##....#......###
...#.....##.###....##.###
#.########.#.#####..##.#.
.#..#..#.#..#....##.#...#
.....#.##..#.#.....##..##
....###....###....###.#..
..#..#..#..#.##.#.#..##.#
.##......#...##.#.#.##.#.
.#####.#.#.##...###...#..
#..###..#....#....##..#..
###..#....#.##.##.....#..
##.##..#.##.#..#####.#.#.
#....#.######.#.#.#.##.#.
###.##.#.######.#..###.#.
#...###.#.#..##..####....
###...##.###..###..##..#.
..##.###...#.....##.##.##
..##..#.###.###.....#.###
#..###.##.#.###......####
#.#...#..##.###.....##.#.
#..#.##...##.##....#...#.
..#.#..#..#...##.#..###..
......###....#.....#....#
""".trim()

    val h = input.lines().size
    val w = input.lines()[0].length

    var part1 = 0
    run {
        val grid = input.toList2d().withIndex2d().flatten().filter { p -> p.second=='#' }.map { it.first }.toHashSet()
        fun Pos.infect() = grid.add(this)
        fun Pos.clean() = grid.remove(this)
        fun Pos.infected() = grid.contains(this)

        var pos = Pos(w / 2, h / 2)
        var dir = Dir.UP

        repeat(10000) {
            if (pos.infected()) {
                dir = dir.turnRight
                pos.clean()
            } else {
                dir = dir.turnLeft
                pos.infect()
                part1++
            }
            pos += dir
        }
    }

    var part2 = 0
    run {
        val grid = input.toList2d().withIndex2d().flatten().filter { p -> p.second=='#' }.mapSecond{_->2}.toHashMap()
        var pos = Pos(w/2,h/2)
        var dir = Dir.UP

        repeat(10000000) {
            grid.getOrPut(pos, {0})
            when (grid[pos]) {
                0 -> dir = dir.turnLeft
                1 -> part2++
                2 -> dir = dir.turnRight
                3 -> dir = dir.turnAround
            }
            grid[pos] = (grid[pos]!!+1)%4
            pos += dir
        }
    }

    println(part1)
    println(part2)
}
