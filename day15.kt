fun main(args: Array<String>) {
    val input =
"""
Generator A starts with 703
Generator B starts with 516
""".trim()

    var a = input.lines()[0].split(" ").last().toLong()
    var b = input.lines()[1].split(" ").last().toLong()
    var part1 = 0
    repeat(40000000) {
        a = 16807*a % 2147483647
        b = 48271*b % 2147483647
        if (a and 0xffff == b and 0xffff)
            part1++
    }

    a = input.lines()[0].split(" ").last().toLong()
    b = input.lines()[1].split(" ").last().toLong()
    var part2 = 0
    repeat(5000000) {
        do { a = 16807*a % 2147483647 } while (a%4 != 0L)
        do { b = 48271*b % 2147483647 } while (b%8 != 0L)
        if (a and 0xffff == b and 0xffff)
            part2++
    }

    println(part1)
    println(part2)
}
