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

        override fun toString() = buildString {
            var a = this@Node
            do {
                this.append("${a.id} ")
                a = a.next
            } while (a != this@Node)
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
    println(part1)
}