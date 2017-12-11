fun main(args: Array<String>) {
    val input = "94,84,0,79,2,27,81,1,123,93,218,23,103,255,254,243"
    val n = 256

    class Knot(var list: IntArray, var curr: Int, var skip: Int) {
        fun twist(lengthSequence: List<Int>) {
            for (v in lengthSequence) {
                list = list.sliceArray((curr until n)+(0 until curr))
                list = list.sliceArray((v-1 downTo 0)+(v until n))
                list = list.sliceArray((n-curr until n)+(0 until n-curr))
                curr = (curr+v+skip)%n
                skip++
            }
        }
    }

    val lengthSequence = input.split(",").map { it.toInt() }
    var knot = Knot(IntArray(n) { it }, 0, 0)
    knot.twist(lengthSequence)
    val part1 = knot.list[0] * knot.list[1]

    val lengthSequence2 = input.map { it.toInt() }+listOf(17, 31, 73, 47, 23)
    knot = Knot(IntArray(n) { it }, 0,0)
    repeat(64) {
        knot.twist(lengthSequence2)
    }
    val xored = knot.list.asIterable().chunked(16).map { it.reduce(Int::xor) }
    val part2 = xored.joinToString("") { String.format("%02x", it) }

    println(part1)
    println(part2)
}
