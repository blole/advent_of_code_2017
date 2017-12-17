fun main(args: Array<String>) {
    val input = "303"
    val steps = input.toInt()

    class Node(val id: Int) {
        var prev: Node = this
        var next: Node = this
        constructor(id: Int, prev: Node, next: Node) : this(id) {
            this.prev = prev
            this.next = next
        }
    }

    var pos = Node(0)
    for (i in 1..2017) {
        repeat(steps) {
            pos = pos.next
        }
        pos.next = Node(i, pos, pos.next)
        pos.next.next.prev = pos.next
        pos = pos.next
    }
    val part1 = pos.next.id

    var part2 = 0
    var curr = 0
    var length = 1
    while (length <= 50000000) {
        if (curr == 0)
            part2 = length
        val adding = (length-curr+1) / (steps+1) + 1
        length += adding
        curr = (curr+adding*(steps+1)) % length
    }

    println(part1)
    println(part2)
}
