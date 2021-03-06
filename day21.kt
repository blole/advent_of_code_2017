import kotlinPuzzleLibrary.*

typealias Pattern = List2d<Char>

fun main(args: Array<String>) {
    val input =
"""
../.. => .##/##./.#.
#./.. => .#./#.#/##.
##/.. => #.#/#.#/###
.#/#. => #../.#./.#.
##/#. => ##./#.#/..#
##/## => #.#/#.#/...
.../.../... => ..##/##../##../#.#.
#../.../... => ##.#/..#./#.#./.#..
.#./.../... => ..#./##.#/#.##/###.
##./.../... => ###./##.#/.###/#.#.
#.#/.../... => ##../#..#/.###/#.#.
###/.../... => ...#/#..#/...#/...#
.#./#../... => ...#/.##./#.##/..#.
##./#../... => .##./.#../.##./.#..
..#/#../... => ####/.#../#.#./.###
#.#/#../... => ###./.#../##../....
.##/#../... => ##../#.#./#.#./##..
###/#../... => #.##/#..#/.#../##..
.../.#./... => .#.#/.###/.##./##..
#../.#./... => .###/.##./..##/..##
.#./.#./... => .##./.#.#/#.##/.###
##./.#./... => ..#./..../..#./###.
#.#/.#./... => ..../..#./..##/##..
###/.#./... => .#.#/#..#/.###/#..#
.#./##./... => ..../..#./.#../####
##./##./... => ..##/#.##/..#./#.##
..#/##./... => ..../#.##/.##./####
#.#/##./... => ..##/#.#./.#../.##.
.##/##./... => #.../...#/###./....
###/##./... => .#../#.#./#.##/....
.../#.#/... => #.#./####/#.../..#.
#../#.#/... => ...#/.#.#/###./.#.#
.#./#.#/... => #..#/#.../###./#.##
##./#.#/... => .##./#.../...#/#.##
#.#/#.#/... => #..#/##../##../.#..
###/#.#/... => #.#./...#/.#.#/.##.
.../###/... => .#.#/.##./..#./.#..
#../###/... => .###/..##/#.##/.#..
.#./###/... => #.../#.../.#../#...
##./###/... => .###/...#/.#.#/.#..
#.#/###/... => .#../..##/#..#/#...
###/###/... => .###/##../##.#/#.#.
..#/.../#.. => ##.#/..../...#/..##
#.#/.../#.. => .#.#/###./...#/.#.#
.##/.../#.. => ##.#/.#../####/#.##
###/.../#.. => #.../#..#/###./....
.##/#../#.. => #..#/..#./####/...#
###/#../#.. => ####/###./##.#/....
..#/.#./#.. => .##./.##./##../#..#
#.#/.#./#.. => #..#/#..#/#.../.#..
.##/.#./#.. => ##../##.#/#.##/..##
###/.#./#.. => #.##/..##/.##./#.#.
.##/##./#.. => #.##/..../##../....
###/##./#.. => ###./.#.#/.###/.#..
#../..#/#.. => .###/#.##/..#./.##.
.#./..#/#.. => #..#/..##/.#.#/##..
##./..#/#.. => ###./#.../..##/##..
#.#/..#/#.. => #.../.##./.###/###.
.##/..#/#.. => ...#/##.#/..#./...#
###/..#/#.. => ###./..#./.#../...#
#../#.#/#.. => #..#/...#/..#./.#.#
.#./#.#/#.. => #..#/##.#/####/.##.
##./#.#/#.. => .###/##../..../.#..
..#/#.#/#.. => ..#./##.#/####/###.
#.#/#.#/#.. => #.#./#.##/##.#/.###
.##/#.#/#.. => ..#./####/##../.###
###/#.#/#.. => .#.#/###./.#.#/#...
#../.##/#.. => .###/..##/.#.#/..#.
.#./.##/#.. => #.##/.#../.###/#.#.
##./.##/#.. => .###/#.../#.../..#.
#.#/.##/#.. => ##../...#/..#./...#
.##/.##/#.. => ..##/.#.#/...#/####
###/.##/#.. => ##../.###/##../###.
#../###/#.. => ###./#..#/#.#./....
.#./###/#.. => ..../#.#./.###/.###
##./###/#.. => .###/##../#..#/####
..#/###/#.. => ..../#.#./#..#/##..
#.#/###/#.. => .#.#/..##/##.#/#..#
.##/###/#.. => .#../...#/##../.#..
###/###/#.. => #.../.###/###./##.#
.#./#.#/.#. => .#.#/#.##/###./#...
##./#.#/.#. => .#../.#../.#../.#..
#.#/#.#/.#. => ##.#/..../###./.#..
###/#.#/.#. => #.#./##.#/.#.#/##..
.#./###/.#. => ##.#/..#./..#./#.#.
##./###/.#. => ####/.###/.#.#/.##.
#.#/###/.#. => .#../.###/##../#.#.
###/###/.#. => #.../.##./..##/####
#.#/..#/##. => ..../..#./##../...#
###/..#/##. => .###/..#./#.##/###.
.##/#.#/##. => .###/..../#.#./...#
###/#.#/##. => ###./...#/.###/####
#.#/.##/##. => #.##/#.../..../...#
###/.##/##. => #.../#.../#..#/...#
.##/###/##. => .#../###./.###/..#.
###/###/##. => ##.#/.#../###./.#..
#.#/.../#.# => #.#./#.#./..../...#
###/.../#.# => ####/###./..../##.#
###/#../#.# => .###/##.#/#.##/..#.
#.#/.#./#.# => ###./.###/#.##/....
###/.#./#.# => .##./###./#.#./##..
###/##./#.# => #.../.#.#/#.##/#..#
#.#/#.#/#.# => ..#./#.#./##../..##
###/#.#/#.# => ..#./.#../...#/.##.
#.#/###/#.# => ..#./###./##.#/####
###/###/#.# => #.../#.#./#..#/.#.#
###/#.#/### => ..##/.##./.#.#/#...
###/###/### => .##./..##/####/###.
""".trim()

    fun Pattern.toInt() = (if (size%2==0) 1 else 32) * (1+flatten().map { if (it=='#') 1 else 0 }.joinToString("").toInt(2))

    val enhancement: HashMap<Int, Pattern> = hashMapOf()
    for (line in input.lines()) {
        fun String.toPattern() = replace("/","\n").toList2d()
        val (i,o) = line.split(" => ")
        for (angle in 0..3) {
            enhancement[i.toPattern().rot90(angle).toInt()] = o.toPattern()
            enhancement[i.toPattern().flipud().rot90(angle).toInt()] = o.toPattern()
        }
    }

    val patterns: MutableList<Pattern> = mutableListOf()
    patterns += ".#.\n..#\n###".toList2d()
    repeat(18) {
        val s = if (patterns.last().size%2 == 0) 2 else 3
        patterns += patterns.last().chunked2d(s,s).map { it.map { enhancement[it.toInt()]!! }.transpose() }.flatten().map { it.flatten() }
    }
    val part1 = patterns[ 5].flatten().count { it == '#' }
    val part2 = patterns[18].flatten().count { it == '#' }

    println(part1)
    println(part2)
}
