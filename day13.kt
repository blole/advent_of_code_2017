import kotlinPuzzleLibrary.prod
import kotlinPuzzleLibrary.product

fun main(args: Array<String>) {
    val input =
"""
0: 4
1: 2
2: 3
4: 4
6: 8
8: 5
10: 6
12: 6
14: 10
16: 8
18: 6
20: 9
22: 8
24: 6
26: 8
28: 8
30: 12
32: 12
34: 12
36: 12
38: 10
40: 12
42: 12
44: 14
46: 8
48: 14
50: 12
52: 14
54: 14
58: 14
60: 12
62: 14
64: 14
66: 12
68: 12
72: 14
74: 18
76: 17
86: 14
88: 20
92: 14
94: 14
96: 18
98: 18
""".trim()

    data class Layer(val depth: Int, val range: Int) {
        val period: Int get() = 2*range-2
    }

    val layers = input.lines().map { it.split(": ") }.map { Layer(it[0].toInt(),it[1].toInt()) }

    val part1 = layers.filter { it.depth%it.period == 0 }.sumBy { it.depth*it.range }
    var part2 = (0..Int.MAX_VALUE).first { delay -> layers.all { (it.depth+delay) % it.period != 0 } }

    println(part1)
    println(part2)
}
