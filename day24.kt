import kotlin.coroutines.experimental.buildSequence
import kotlin.math.min

fun main(args: Array<String>) {
    val input =
"""
32/31
2/2
0/43
45/15
33/24
20/20
14/42
2/35
50/27
2/17
5/45
3/14
26/1
33/38
29/6
50/32
9/48
36/34
33/50
37/35
12/12
26/13
19/4
5/5
14/46
17/29
45/43
5/0
18/18
41/22
50/3
4/4
17/1
40/7
19/0
33/7
22/48
9/14
50/43
26/29
19/33
46/31
3/16
29/46
16/0
34/17
31/7
5/27
7/4
49/49
14/21
50/9
14/44
29/29
13/38
31/11
""".trim()

    data class Domino(val a: Int, val b: Int)

    fun strongestBridge(startingPort: Int, dominoes: List<Domino>, dominoValue: (Domino)->Int): Int {
        val taken: MutableList<Boolean> = MutableList(dominoes.size, {false})
        fun strongestBridgeEnd(port: Int): Int = buildSequence {
            for ((i, domino) in dominoes.withIndex().filterNot { (i,_) -> taken[i] }) {
                taken[i] = true
                if (domino.a == port)
                    yield(dominoValue(domino) + strongestBridgeEnd(domino.b))
                else if (domino.b == port)
                    yield(dominoValue(domino) + strongestBridgeEnd(domino.a))
                taken[i] = false
            }
        }.max() ?: 0
        return strongestBridgeEnd(startingPort)
    }

    val dominoes = input.lines().map { Domino(it.split("/")[0].toInt(), it.split("/")[1].toInt()) }
    val part1 = strongestBridge(0, dominoes, {          it.a + it.b })
    val part2 = strongestBridge(0, dominoes, { 100000 + it.a + it.b }) % 100000

    println(part1)
    println(part2)
}
