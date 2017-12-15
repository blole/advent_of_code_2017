import kotlinPuzzleLibrary.*

fun main(args: Array<String>) {
    val input = "amgozmfv"

    fun Int.toBin() = "%4s".format(Integer.toBinaryString(this)).replace(" ","0")
    fun Char.fromHex() = if (this >= 'a') this-'a'+10 else this-'0'
    fun String.toBin() = map { it.fromHex().toBin() }.joinToString("")

    val disk = (0..127).map { knotHash("$input-$it").toBin() }
    val part1 = disk.sumBy { it.count {it == '1'} }

    var part2 = 0
    val unvisited = array2dOfBoolean(130,130)
    disk.forEachIndexed { x,s -> s.forEachIndexed { y, c -> if (disk[x][y] == '1') unvisited[x+1][y+1] = true } }
    val queue: MutableList<Pos> = mutableListOf()
    for (pos: Pos in (1..129).productSelf().map { it.toPos() }) {
        if (unvisited[pos]) {
            queue += pos
            part2++
            while (queue.isNotEmpty()) {
                val pos = queue.removeAt(queue.lastIndex)
                if (unvisited[pos]) {
                    queue.add(pos.up)
                    queue.add(pos.down)
                    queue.add(pos.left)
                    queue.add(pos.right)
                }
                unvisited[pos] = false
            }
        }
    }

    println(part1)
    println(part2)
}
