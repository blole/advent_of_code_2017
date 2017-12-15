import kotlinPuzzleLibrary.prod

fun main(args: Array<String>) {
    val input = "94,84,0,79,2,27,81,1,123,93,218,23,103,255,254,243"

    val lengthSequence = input.split(",").map { it.toInt() }
    val part1 = Knot().twist(lengthSequence).list.take(2).prod()
    val part2 = knotHash(input)

    println(part1)
    println(part2)
}

class Knot(var list: IntArray = IntArray(256) { it }, var curr: Int = 0, var skip: Int = 0) {
    fun twist(lengthSequence: List<Int>): Knot {
        val n = list.size
        for (v in lengthSequence) {
            list = list.sliceArray((curr until n)+(0 until curr))
            list = list.sliceArray((v-1 downTo 0)+(v until n))
            list = list.sliceArray((n-curr until n)+(0 until n-curr))
            curr = (curr+v+skip)%n
            skip++
        }
        return this
    }
}

fun knotHash(lengthSequence: List<Int>): String {
    var knot = Knot(IntArray(256) { it }, 0, 0)
    repeat(64) {
        knot.twist(lengthSequence)
    }
    val xored = knot.list.asIterable().chunked(16).map { it.reduce(Int::xor) }
    return xored.joinToString("") { String.format("%02x", it) }
}
fun knotHash(string: String): String = knotHash(string.map { it.toInt() }+listOf(17, 31, 73, 47, 23))
