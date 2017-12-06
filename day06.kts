val input = "11 11 13 7 0 15 5 5 4 4 1 1 7 1 15 11"

val banks: MutableList<Int> = input.split(" ").map { it.toInt() }.toMutableList()
val seen: MutableMap<List<Int>, Int> = mutableMapOf()
val n: Int = banks.size

var part1 = 0

while (banks !in seen.keys) {
    seen.put(banks, part1)
    val (i,v) = banks.withIndex().maxBy { it.value }!!
    banks[i] = 0

    var rem = v%n
    for (j in (i+1..n-1) + (0..i))
        banks[j] += v/n + Math.max(0, Math.min(rem--, 1))

    part1++
}

val part2 = part1 - seen[banks]!!

println(part1)
println(part2)
